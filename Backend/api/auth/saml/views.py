from django.shortcuts import redirect, HttpResponse
from django.views.decorators.csrf import csrf_exempt
from django.conf import settings
from onelogin.saml2.auth import OneLogin_Saml2_Auth
import json
from django.http import JsonResponse
from rest_framework_simplejwt.tokens import AccessToken, RefreshToken, TokenError
from django.contrib.auth.models import User
from django.contrib.auth import get_user_model
from datetime import datetime, timedelta
from api.models import UserSession
from django.utils.timezone import now
from django.core.exceptions import ObjectDoesNotExist
from rest_framework import status


def prepare_django_request(request):
    """
    Converts Django request to the format required by OneLogin's SAML SDK
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

    print("Generated SAML Request URL:", saml_auth.login())

    return redirect(saml_auth.login())

User = get_user_model()

@csrf_exempt
def acs(request):
    """
    Processes the SAML response from Microsoft and logs the user in.
    """
    if request.method != "POST":
        return JsonResponse({"error": "ACS endpoint only supports POST requests"}, status=405)

    # Prepare SAML request
    req = {
        "https": "on" if request.is_secure() else "off",
        "http_host": request.get_host(),
        "script_name": request.path,
        "get_data": request.GET.copy(),
        "post_data": request.POST.copy(),
    }

    saml_auth = OneLogin_Saml2_Auth(req, settings.SAML_SETTINGS)
    saml_auth.process_response()

    errors = saml_auth.get_errors()
    if errors:
        return JsonResponse({"error": "SAML Authentication failed", "details": errors}, status=400)

    # Retrieve username from SAML response
    username = saml_auth.get_nameid()

    # Check if user exists; if not, create it
    user, created = User.objects.get_or_create(username=username, defaults={"email": username})
    
    if created:
        user.save()
        print(f"New user created: {username}")

    # *** Single session check without token expiration ***
    try:
        existing_session = UserSession.objects.get(user=user)
        if existing_session.is_active:
            # Active session exists; redirect with error parameter.
            login_redirect_url = f"{settings.SSO_SP_REDIRECT}/login?error=session_active"
            return redirect(login_redirect_url)
    except UserSession.DoesNotExist:
        pass

    # Generate JWT tokens for the user
    access_token = AccessToken.for_user(user)
    refresh_token = RefreshToken.for_user(user)

    expires_at = datetime.now() + timedelta(minutes=5)  # Set expiration
    session, created = UserSession.objects.update_or_create(
        user=user,
        defaults={
            "access_token": str(access_token),
            "refresh_token": str(refresh_token),
            "expires_at": expires_at,
            "is_active": True,
        }
    )

    # Redirect to frontend with username and access token
    frontend_redirect_url = f"{settings.SSO_SP_REDIRECT}?username={username}&access={access_token}"
    print(f"Redirecting to: {frontend_redirect_url}")
    return redirect(frontend_redirect_url)



