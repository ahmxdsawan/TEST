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

    #  Retrieve username from SAML response
    username = saml_auth.get_nameid()

    #  Check if user exists, if not, create it
    user, created = User.objects.get_or_create(username=username, defaults={"email": username})
    
    if created:
        # user.set_password(User.objects.make_random_password())  # Set a random password
        user.save()
        print(f"New user created: {username}")

    try:
        existing_session = UserSession.objects.get(user=user)
        existing_token = existing_session.access_token

        # Check if token is still valid
        try:
            existing_session = UserSession.objects.get(user=user)
        
            #  If session is inactive, allow login
            if not existing_session.is_active:
                print("Inactive session detected. Allowing login.")
                existing_session.delete()  # Remove stale session

            else:
                access_token_obj = AccessToken(existing_session.access_token)
                if access_token_obj["exp"] > now().timestamp():
                    print("User already has an active session. Redirecting to login.")
                    login_redirect_url = f"{settings.SSO_SP_REDIRECT}/login?error=session_active"
                    return redirect(login_redirect_url)

        except TokenError:
            print("Previous session expired. Allowing new login.")
            existing_session.delete()  # Remove expired session

    except ObjectDoesNotExist:
        pass  # No active session exists, allow login

    #  Generate JWT token for the user
    access_token = AccessToken.for_user(user)
    refresh_token = RefreshToken.for_user(user)

    expires_at = datetime.now() + timedelta(minutes=5)  # Set expiration
    session, created = UserSession.objects.update_or_create(
        user=user,
        defaults={"access_token": str(access_token), "refresh_token": str(refresh_token), "expires_at": expires_at, "is_active": True},
    )

    #  Redirect to frontend with username and access token
    frontend_redirect_url = f"{settings.SSO_SP_REDIRECT}?username={username}&access={access_token}"

    print(f"Redirecting to: {frontend_redirect_url}")  #  Debugging

    return redirect(frontend_redirect_url)



