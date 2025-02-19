# custom_jwt_authentication.py
from rest_framework_simplejwt.authentication import JWTAuthentication
from rest_framework_simplejwt.tokens import AccessToken
from rest_framework import exceptions

class CookieJWTAuthentication(JWTAuthentication):
    def authenticate(self, request):
        token = request.COOKIES.get('access_token')
        if not token:
            return None

        try:
            validated_token = AccessToken(token)
            user = self.get_user(validated_token)
            return (user, validated_token)
        except Exception as e:
            print(f"Token validation error: {str(e)}")  # Add logging
            return None  # Return None instead of raising exception