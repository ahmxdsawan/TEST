import json
import os
from onelogin.saml2.auth import OneLogin_Saml2_Auth
from django.http import JsonResponse
from django.conf import settings

def get_saml_settings():
    """Retrieve SAML settings from environment variables"""
    return settings.SAML_SETTINGS


def init_saml_auth(request):
    """Initialize OneLogin SAML Authentication"""
    saml_settings = get_saml_settings()
    
    req = {
        "https": "on" if request.is_secure() else "off",
        "http_host": request.get_host(),
        "script_name": request.path,
        "get_data": request.GET.copy(),
        "post_data": request.POST.copy(),
    }

    return OneLogin_Saml2_Auth(req, saml_settings)
