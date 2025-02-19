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

    # Get (or create) the user.
    user, created = User.objects.get_or_create(username=username, defaults={"email": username})
    if created:
        user.save()
        print(f"New user created: {username}")

    # Enforce single-session: if an active session exists, redirect with an error.
    try:
        existing_session = UserSession.objects.get(user=user)
        if existing_session.is_active:
            login_redirect_url = f"{settings.SSO_SP_REDIRECT}/login?error=session_active"
            return redirect(login_redirect_url)
    except UserSession.DoesNotExist:
        pass

    # Generate JWT tokens.
    refresh = RefreshToken.for_user(user)
    access_token = refresh.access_token
    access_token["username"] = user.username

    # Get current time and expiration time
    current_time = timezone.now()
    expires_at = current_time + refresh.access_token.lifetime

    expires_at_iso = expires_at.strftime('%Y-%m-%dT%H:%M:%S.%fZ')


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

    # Build the frontend redirect URL (only include the username).
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
        samesite="None",
        expires=expires_at,
        path="/"
    )
    response.set_cookie(
        key="refresh_token",
        value=str(refresh),
        httponly=True,
        secure=is_secure,
        samesite="None",
        max_age=60 * 60 * 24,  # 1 day (adjust as needed)
        path="/"
    )
    return response