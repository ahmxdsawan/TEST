from django.core.exceptions import ObjectDoesNotExist
from rest_framework_simplejwt.tokens import RefreshToken, AccessToken
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from django.contrib.auth import authenticate
from rest_framework.permissions import IsAuthenticated
from .models import UserSession
from drf_yasg.utils import swagger_auto_schema
from drf_yasg import openapi
from django.utils.timezone import now, timezone
from api.custom_jwt_authentication import CookieJWTAuthentication
from rest_framework_simplejwt.authentication import JWTAuthentication
from api import models
from django.contrib.auth import login
from django.utils import timezone

import hashlib

class LoginView(APIView):
    @swagger_auto_schema(
        operation_description="Login and set secure JWT tokens in HttpOnly cookies",
        request_body=openapi.Schema(
            type=openapi.TYPE_OBJECT,
            properties={
                'username': openapi.Schema(type=openapi.TYPE_STRING, description='User username'),
                'password': openapi.Schema(type=openapi.TYPE_STRING, description='User password'),
            },
            required=['username', 'password'],
        ),
        responses={200: "Logged in successfully", 401: "Invalid credentials"}
    )
    def post(self, request, *args, **kwargs):
        try:
            username = request.data.get("username")
            password = request.data.get("password")
            if not username or not password:
                return Response({"detail": "Username and password are required"},
                                status=status.HTTP_400_BAD_REQUEST)
            user = authenticate(username=username, password=password)
            if not user:
                return Response({"detail": "Invalid credentials"},
                                status=status.HTTP_401_UNAUTHORIZED)
            
            # Validate that the user exists.
            try:
                # First try to retrieve from UsersUserCi.
                cseye_user = models.UsersUsers.objects.get(username__iexact=username)
            except models.UsersUserCi.DoesNotExist:
                try:
                    # Try the UsersUsers table.
                    cseye_user = models.UsersUsers.objects.get(username__iexact=username)
                except models.UsersUsers.DoesNotExist:
                    return Response({"error": "User does not exist in custom tables"},
                                    status=status.HTTP_401_UNAUTHORIZED)
            if not cseye_user.isActive:
                return Response({"error": "User is disabled"}, status=status.HTTP_403_FORBIDDEN)

            # Enforce single-session: if an active session exists, deny new login.
            try:
                existing_session = UserSession.objects.get(user=user)
                if existing_session.is_active:
                    return Response({"detail": "User already has an active session."},
                                    status=status.HTTP_403_FORBIDDEN)
            except UserSession.DoesNotExist:
                pass

            # Log the user in 
            login(request, user)

            # Generate JWT tokens
            refresh = RefreshToken.for_user(user)
            access_token = refresh.access_token

            # Extend token payload with additional fields.
            access_token["first_name"] = user.first_name
            access_token["last_name"] = user.last_name
            access_token["username"] = user.username
            access_token["email"] = user.email

            # Retrieve roles and encode them using MD5.
            encoded_roles = []
            roles_qs = models.UsersUserRl.objects.filter(user=cseye_user)  # Adjust query as needed
            for role_entry in roles_qs:
                role_name = role_entry.role.role_name  
                encoded_roles.append(hashlib.md5(role_name.encode('utf-8')).hexdigest())
            access_token["roles"] = encoded_roles

            # Set the expiry time in ISO format.
            current_time = now()
            expires_at = current_time + refresh.access_token.lifetime
            access_token["expiry"] = expires_at.isoformat()

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

            response = Response({
                "detail": "Logged in successfully",
                "username": user.username,
                "expires_at": expires_at.isoformat()
            }, status=status.HTTP_200_OK)

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
                max_age=60 * 60 * 24,  # 1 day
                path="/"
            )
            return response

        except Exception as e:
            import traceback
            traceback.print_exc()
            return Response({"detail": "An unexpected error occurred"},
                            status=status.HTTP_500_INTERNAL_SERVER_ERROR)


class LogoutView(APIView):
    permission_classes = [IsAuthenticated]
    authentication_classes = [CookieJWTAuthentication]

    @swagger_auto_schema(
        operation_description="Logout and remove user session",
        manual_parameters=[
            openapi.Parameter('Authorization', openapi.IN_HEADER, description="Bearer Token", type=openapi.TYPE_STRING)
        ],
        responses={200: "Logged out successfully", 401: "Unauthorized"},
    )
    def post(self, request):
        user = request.user if request.user.is_authenticated else None
        if user:
            try:
                session = UserSession.objects.filter(user=user).first()
                if session:
                    session.is_active = False
                    session.save()
                response = Response({"detail": "Logged out successfully"}, status=status.HTTP_200_OK)
                response.delete_cookie("access_token", path="/")
                response.delete_cookie("refresh_token", path="/")
                return response
            except Exception as e:
                print(f"Error during logout: {str(e)}")
                return Response({"detail": f"Error during logout: {str(e)}"},
                                status=status.HTTP_500_INTERNAL_SERVER_ERROR)
        return Response({"detail": "User not authenticated"}, status=status.HTTP_401_UNAUTHORIZED)


class ForcedLogoutView(APIView):
    # Open endpoint so that forced logout works even if the token is expired.
    authentication_classes = []
    permission_classes = []
    
    def post(self, request):
        print("Forced logout request received")
        
        token = request.COOKIES.get("access_token")
        if token:
            session = UserSession.objects.filter(access_token=token).first()
            if session:
                session.is_active = False
                session.save()
                print("Session marked as inactive for token:", token)
            else:
                print("No session found for token:", token)
        else:
            print("No access_token found in cookies.")
            
        response = Response({"detail": "Logged out successfully"}, status=status.HTTP_200_OK)
        response.delete_cookie("access_token", path="/")
        response.delete_cookie("refresh_token", path="/")
        return response



# class GetTokenView(APIView):
#     authentication_classes = [CookieJWTAuthentication]
#     permission_classes = []

#     def get(self, request):
#         print("Get-token request received")

#         if not request.user or not request.user.is_authenticated:
#             print("User not authenticated")
#             return Response({"username": None}, status=status.HTTP_200_OK)

#         try:
#             session = UserSession.objects.get(user=request.user, is_active=True)
#             print(f"Found active session for: {request.user.username}")
            
#             # Add expiration time from the session
#             return Response({
#                 "username": request.user.username,
#                 "session_active": True,
#                 "expires_at": session.expires_at.isoformat() if session.expires_at else None
#             }, status=status.HTTP_200_OK)
#         except UserSession.DoesNotExist:
#             print(f"No active session found for: {request.user.username}")
#             return Response({"username": None}, status=status.HTTP_200_OK)

class GetTokenView(APIView):
    authentication_classes = [CookieJWTAuthentication]
    permission_classes = []

    def get(self, request):

        if not request.user or not request.user.is_authenticated:
            return Response({"username": None}, status=status.HTTP_200_OK)

        try:
            # Check if there's an active session for this user
            session = UserSession.objects.get(
                user=request.user,
                is_active=True,
                expires_at__gt=timezone.now()  # Add expiration check
            )
            
            # Check if this is the same browser/device
            current_token = request.COOKIES.get('access_token')
            if current_token != session.access_token:
                return Response({
                    "error": "Session active in another browser/device",
                    "username": None,
                    "session_active": True
                }, status=status.HTTP_403_FORBIDDEN)

            return Response({
                "username": request.user.username,
                "session_active": True,
                "expires_at": session.expires_at.isoformat() if session.expires_at else None
            }, status=status.HTTP_200_OK)

        except UserSession.DoesNotExist:
            return Response({"username": None}, status=status.HTTP_200_OK)



