from django.shortcuts import redirect
from django.views.decorators.csrf import csrf_exempt
from django.conf import settings
from onelogin.saml2.auth import OneLogin_Saml2_Auth
from django.http import JsonResponse
from rest_framework_simplejwt.tokens import AccessToken, RefreshToken
from django.contrib.auth import get_user_model
from datetime import timedelta
from api.models import UserSession
from django.utils.timezone import now
from django.core.exceptions import ObjectDoesNotExist
from rest_framework import status
from rest_framework.response import Response
from django.utils import timezone
from datetime import datetime, timedelta
import hashlib
from api import models
from django.utils import timezone
from urllib.parse import quote


def prepare_django_request(request):
    """
    Converts Django request to the format required by OneLogin's SAML SDK.
    """
    return {
        'https': 'on' if request.is_secure() else 'off',
        'http_host': request.get_host(),
        'script_name': request.path,
        'get_data': request.GET.copy(),
        'post_data': request.POST.copy(),
    }

@csrf_exempt
def microsoft_login(request):
    """
    Generates a SAML authentication request and redirects the user to Microsoft login.
    """
    req = prepare_django_request(request)
    saml_auth = OneLogin_Saml2_Auth(req, settings.SAML_SETTINGS)
    return redirect(saml_auth.login())

User = get_user_model()

@csrf_exempt
def acs(request):
    """
    Processes the SAML response from Microsoft and logs the user in.
    Generates JWT tokens, sets them as HttpOnly cookies, and then
    redirects the user to the frontend with the username in the query.
    """
    if request.method != "POST":
        return JsonResponse({"error": "ACS endpoint only supports POST requests"}, status=405)

    # Prepare SAML request for processing.
    req = prepare_django_request(request)
    saml_auth = OneLogin_Saml2_Auth(req, settings.SAML_SETTINGS)
    saml_auth.process_response()

    errors = saml_auth.get_errors()
    if errors:
        return JsonResponse({"error": "SAML Authentication failed", "details": errors}, status=400)

    # Retrieve the username from the SAML response.
    username = saml_auth.get_nameid()

    try:
        user = User.objects.get(email=username)
    except User.DoesNotExist:
        return JsonResponse({"error": "User does not exist"}, status=404)
    
    # Validate that the user exists in your custom tables.
    try:
        # Here we assume the username is an email.
        cseye_user = models.UsersUserCi.objects.get(contact_value=username, contact_type='email').user
    except models.UsersUserCi.DoesNotExist:
        try:
            cseye_user = models.UsersUsers.objects.get(username__iexact=username)
        except models.UsersUsers.DoesNotExist:
            return JsonResponse({"error": "User does not exist in custom tables"}, status=401)
    if not cseye_user.isActive:
        return JsonResponse({"error": "User is disabled"}, status=403)


    # Enforce single-session: if an active session exists, redirect with an error.
    try:
        existing_session = UserSession.objects.get(
            user=user, 
            is_active=True,
            expires_at__gt=timezone.now()
        )
        print("Active session detected during SAML login")
        message = quote("You have an active session in another browser. Please logout first.")
        login_redirect_url = f"{settings.SSO_SP_REDIRECT}/login?error=session_active&message={message}"
        return redirect(login_redirect_url)
    except UserSession.DoesNotExist:
        pass

    # Generate JWT tokens.
    refresh = RefreshToken.for_user(user)
    access_token = refresh.access_token

    # Add basic user details.
    access_token["first_name"] = user.first_name
    access_token["last_name"] = user.last_name
    access_token["username"] = user.username
    access_token["email"] = user.email

    # Retrieve roles from your custom user roles table and encode them using MD5.
    encoded_roles = []
    roles_qs = models.UsersUserRl.objects.filter(user=cseye_user)  # Adjust query as needed
    for role_entry in roles_qs:
        # Assuming each role_entry has a related role with a role_name field.
        role_name = role_entry.role.role_name  
        encoded_roles.append(hashlib.md5(role_name.encode('utf-8')).hexdigest())
    access_token["roles"] = encoded_roles

    # Get current time and expiration time
    current_time = timezone.now()
    expires_at = current_time + refresh.access_token.lifetime
    expires_at_iso = expires_at.strftime('%Y-%m-%dT%H:%M:%S.%fZ')
    access_token["expiry"] = expires_at_iso


    # Create or update the user session.
    UserSession.objects.update_or_create(
        user=user,
        defaults={
            "access_token": str(access_token),
            "refresh_token": str(refresh),
            "expires_at": expires_at,
            "is_active": True
        }
    )

    # Build the frontend redirect URL.
    frontend_redirect_url = f"{settings.SSO_SP_REDIRECT}?username={username}&expires_at={expires_at_iso}"
    response = redirect(frontend_redirect_url)

    # Here we set the cookies without hard-coding a domain.
    # (Omit the "domain" parameter so the browser uses the response’s domain.)
    # In production, set is_secure=True.
    is_secure = True
    response.set_cookie(
        key="access_token",
        value=str(access_token),
        httponly=True,
        secure=is_secure,
        samesite="Lax",
        expires=expires_at,
        path="/"
    )
    response.set_cookie(
        key="refresh_token",
        value=str(refresh),
        httponly=True,
        secure=is_secure,
        samesite="Lax",
        max_age=60 * 60 * 24,  # 1 day (adjust as needed)
        path="/"
    )
    return response