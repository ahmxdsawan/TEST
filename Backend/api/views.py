from django.core.exceptions import ObjectDoesNotExist
from rest_framework_simplejwt.tokens import RefreshToken, AccessToken, TokenError
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from django.contrib.auth import authenticate
from rest_framework.permissions import IsAuthenticated
from .models import UserSession
from rest_framework_simplejwt.authentication import JWTAuthentication
from rest_framework_simplejwt.tokens import RefreshToken
from rest_framework.permissions import IsAuthenticated
from rest_framework_simplejwt.authentication import JWTAuthentication
from drf_yasg.utils import swagger_auto_schema
from drf_yasg import openapi
from django.utils.timezone import now

class LoginView(APIView):
    @swagger_auto_schema(
        operation_description="Login and get access/refresh tokens",
        request_body=openapi.Schema(
            type=openapi.TYPE_OBJECT,
            properties={
                'username': openapi.Schema(type=openapi.TYPE_STRING, description='User username'),
                'password': openapi.Schema(type=openapi.TYPE_STRING, description='User password'),
            },
            required=['username', 'password'],
        ),
        responses={200: "Tokens returned successfully", 401: "Invalid credentials"}
    )
    def post(self, request, *args, **kwargs):
        try:
            # Log the incoming request data (excluding password)
            print(f"Login attempt for username: {request.data.get('username')}")

            username = request.data.get("username")
            password = request.data.get("password")

            if not username or not password:
                return Response(
                    {"detail": "Username and password are required"}, 
                    status=status.HTTP_400_BAD_REQUEST
                )

            user = authenticate(username=username, password=password)
            
            if not user:
                print(f"Authentication failed for username: {username}")
                return Response(
                    {"detail": "Invalid credentials"}, 
                    status=status.HTTP_401_UNAUTHORIZED
                )

            try:
                existing_session = UserSession.objects.get(user=user)

                if not existing_session.is_active:
                    print(f"Inactive session found for user {username}. Removing...")
                    existing_session.delete()
                else:
                    try:
                        access_token_obj = AccessToken(existing_session.access_token)
                        if access_token_obj["exp"] > now().timestamp():
                            print(f"Active session exists for user {username}")
                            return Response(
                                {"detail": "User is already logged in from another session."}, 
                                status=status.HTTP_403_FORBIDDEN
                            )
                    except TokenError as e:
                        print(f"Token error for existing session: {str(e)}")
                        existing_session.delete()

            except ObjectDoesNotExist:
                print(f"No existing session found for user {username}")
                pass

            # Generate new tokens
            refresh = RefreshToken.for_user(user)
            access_token = refresh.access_token
            access_token["username"] = user.username
            expires_at = now() + refresh.access_token.lifetime

            # Store new session
            session, created = UserSession.objects.update_or_create(
                user=user,
                defaults={
                    "access_token": str(access_token),
                    "refresh_token": str(refresh),
                    "expires_at": expires_at,
                    "is_active": True
                }
            )

            print(f"Login successful for user {username}")
            return Response({
                "access": str(access_token),
                "refresh": str(refresh),
                "username": user.username
            })

        except Exception as e:
            print(f"Unexpected error during login: {str(e)}")
            import traceback
            traceback.print_exc()
            return Response(
                {"detail": "An unexpected error occurred"}, 
                status=status.HTTP_500_INTERNAL_SERVER_ERROR
            )

#  Logout API (removes session from database)
class LogoutView(APIView):
    permission_classes = [IsAuthenticated]
    authentication_classes = [JWTAuthentication]
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
            UserSession.objects.filter(user=user).delete()
            return Response({"detail": "Logged out successfully"}, status=status.HTTP_200_OK)

        return Response({"detail": "User not authenticated"}, status=status.HTTP_401_UNAUTHORIZED)

class GetTokenView(APIView):
    permission_classes = [IsAuthenticated]
    authentication_classes = [JWTAuthentication]

    def get(self, request):
        try:
            session = UserSession.objects.get(user=request.user)
            return Response({"token": session.token})
        except ObjectDoesNotExist:
            return Response({"token": None}, status=status.HTTP_404_NOT_FOUND)
        
class MarkInactiveView(APIView):
    def post(self, request):
        token = request.data.get("token")

        try:
            session = UserSession.objects.get(access_token=token)
            session.is_active = False  # Mark session as inactive
            session.save()
            return Response({"detail": "Session marked as inactive"}, status=status.HTTP_200_OK)
        except ObjectDoesNotExist:
            return Response({"detail": "Session not found"}, status=status.HTTP_404_NOT_FOUND)


# from django.core.exceptions import ObjectDoesNotExist
# from django.utils.timezone import now
# from django.contrib.auth import authenticate
# from rest_framework.views import APIView
# from rest_framework.response import Response
# from rest_framework import status
# from rest_framework.permissions import IsAuthenticated
# from rest_framework_simplejwt.tokens import RefreshToken, AccessToken, TokenError
# from rest_framework_simplejwt.authentication import JWTAuthentication
# from drf_yasg.utils import swagger_auto_schema
# from drf_yasg import openapi
# from .models import UserSession


# class LoginView(APIView):
#     """Handles user authentication and session management."""

#     @swagger_auto_schema(
#         operation_description="Login and get access/refresh tokens",
#         request_body=openapi.Schema(
#             type=openapi.TYPE_OBJECT,
#             properties={
#                 'username': openapi.Schema(type=openapi.TYPE_STRING, description='User username'),
#                 'password': openapi.Schema(type=openapi.TYPE_STRING, description='User password'),
#             },
#             required=['username', 'password'],
#         ),
#         responses={200: "Tokens returned successfully", 401: "Invalid credentials"}
#     )
#     def post(self, request):
#         """Authenticate user and create session."""
#         username = request.data.get("username")
#         password = request.data.get("password")
#         user = authenticate(username=username, password=password)

#         if not user:
#             return Response({"detail": "Invalid credentials"}, status=status.HTTP_401_UNAUTHORIZED)

#         #  Remove expired session if it exists
#         UserSession.objects.filter(user=user, expires_at__lt=now()).delete()

#         #  Check if user already has an active session
#         session, created = UserSession.objects.get_or_create(user=user)
#         if not created and session.access_token:
#             try:
#                 access_token = AccessToken(session.access_token)
#                 if not access_token.is_expired():
#                     return Response(
#                         {"detail": "User is already logged in from another session."},
#                         status=status.HTTP_403_FORBIDDEN
#                     )
#             except TokenError:
#                 session.delete()

#         #  Generate new tokens and save session
#         refresh = RefreshToken.for_user(user)
#         access_token = refresh.access_token

#         session.access_token = str(access_token)
#         session.refresh_token = str(refresh)
#         session.expires_at = now() + refresh.access_token.lifetime
#         session.save()

#         return Response({
#             "access": str(access_token),
#             "refresh": str(refresh),
#             "username": user.username
#         })


# class LogoutView(APIView):
#     """Handles user logout and session removal."""

#     permission_classes = [IsAuthenticated]
#     authentication_classes = [JWTAuthentication]

#     @swagger_auto_schema(
#         operation_description="Logout and remove user session",
#         manual_parameters=[
#             openapi.Parameter('Authorization', openapi.IN_HEADER, description="Bearer Token", type=openapi.TYPE_STRING)
#         ],
#         responses={200: "Logged out successfully", 401: "Unauthorized"},
#     )
#     def post(self, request):
#         """Logout user and delete session."""
#         user = request.user
#         UserSession.objects.filter(user=user).delete()
#         return Response({"detail": "Logged out successfully"}, status=status.HTTP_200_OK)


# class GetTokenView(APIView):
#     """Retrieves the stored session token for an authenticated user."""

#     permission_classes = [IsAuthenticated]
#     authentication_classes = [JWTAuthentication]

#     def get(self, request):
#         """Fetch access token from stored session."""
#         try:
#             session = UserSession.objects.get(user=request.user)
#             return Response({"token": session.access_token})
#         except ObjectDoesNotExist:
#             return Response({"token": None}, status=status.HTTP_404_NOT_FOUND)
