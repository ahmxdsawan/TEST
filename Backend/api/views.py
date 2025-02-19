# from django.core.exceptions import ObjectDoesNotExist
# from rest_framework_simplejwt.tokens import RefreshToken, AccessToken, TokenError
# from rest_framework.views import APIView
# from rest_framework.response import Response
# from rest_framework import status
# from django.contrib.auth import authenticate
# from rest_framework.permissions import IsAuthenticated
# from .models import UserSession
# from rest_framework_simplejwt.authentication import JWTAuthentication
# from rest_framework_simplejwt.tokens import RefreshToken
# from rest_framework.permissions import IsAuthenticated
# from rest_framework_simplejwt.authentication import JWTAuthentication
# from drf_yasg.utils import swagger_auto_schema
# from drf_yasg import openapi
# from django.utils.timezone import now

# class LoginView(APIView):
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
#     def post(self, request, *args, **kwargs):
#         try:
#             username = request.data.get("username")
#             password = request.data.get("password")

#             if not username or not password:
#                 return Response(
#                     {"detail": "Username and password are required"}, 
#                     status=status.HTTP_400_BAD_REQUEST
#                 )

#             user = authenticate(username=username, password=password)
            
#             if not user:
#                 return Response(
#                     {"detail": "Invalid credentials"}, 
#                     status=status.HTTP_401_UNAUTHORIZED
#                 )

#             # Check for any active session regardless of token expiration.
#             try:
#                 existing_session = UserSession.objects.get(user=user)
#                 if existing_session.is_active:
#                     # Active session exists; do not allow new login.
#                     return Response(
#                         {"detail": "User already has an active session."},
#                         status=status.HTTP_403_FORBIDDEN
#                     )
#             except UserSession.DoesNotExist:
#                 pass

#             # Generate new tokens
#             refresh = RefreshToken.for_user(user)
#             access_token = refresh.access_token
#             access_token["username"] = user.username
#             expires_at = now() + refresh.access_token.lifetime

#             # Store new session (or overwrite the inactive one)
#             session, created = UserSession.objects.update_or_create(
#                 user=user,
#                 defaults={
#                     "access_token": str(access_token),
#                     "refresh_token": str(refresh),
#                     "expires_at": expires_at,
#                     "is_active": True
#                 }
#             )

#             print(f"Login successful for user {username}")
#             return Response({
#                 "access": str(access_token),
#                 "refresh": str(refresh),
#                 "username": user.username
#             })

#         except Exception as e:
#             print(f"Unexpected error during login: {str(e)}")
#             import traceback
#             traceback.print_exc()
#             return Response(
#                 {"detail": "An unexpected error occurred"}, 
#                 status=status.HTTP_500_INTERNAL_SERVER_ERROR
#             )


# #  Logout API (removes session from database)
# class LogoutView(APIView):
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
#         print("Logout request received")  # Add logging
#         user = request.user if request.user.is_authenticated else None

#         if user:
#             try:
#                 print(f"Processing logout for user: {user.username}")  # Add logging
#                 session = UserSession.objects.filter(user=user).first()
#                 if session:
#                     print(f"Found active session for user: {user.username}")  # Add logging
#                     session.is_active = False
#                     session.save()
#                     print("Session marked as inactive")  # Add logging
#                     return Response({"detail": "Logged out successfully"}, status=status.HTTP_200_OK)
#                 else:
#                     print(f"No active session found for user: {user.username}")  # Add logging
#                     return Response({"detail": "No active session found"}, status=status.HTTP_200_OK)
#             except Exception as e:
#                 print(f"Error during logout: {str(e)}")  # Add logging
#                 return Response(
#                     {"detail": f"Error during logout: {str(e)}"}, 
#                     status=status.HTTP_500_INTERNAL_SERVER_ERROR
#                 )

#         print("User not authenticated")  # Add logging
#         return Response({"detail": "User not authenticated"}, status=status.HTTP_401_UNAUTHORIZED)

# class GetTokenView(APIView):
#     permission_classes = [IsAuthenticated]
#     authentication_classes = [JWTAuthentication]

#     def get(self, request):
#         try:
#             session = UserSession.objects.get(user=request.user, is_active=True)
#             return Response({"token": session.access_token})
#         except ObjectDoesNotExist:
#             return Response({"detail": "Session not found"}, status=status.HTTP_404_NOT_FOUND)
#         except Exception as e:
#             # Print the traceback to the server console
#             return Response({"detail": str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)
        
# class MarkInactiveView(APIView):
#     def post(self, request):
#         token = request.data.get("token")

#         try:
#             session = UserSession.objects.get(access_token=token)
#             session.is_active = False  # Mark session as inactive
#             session.save()
#             return Response({"detail": "Session marked as inactive"}, status=status.HTTP_200_OK)
#         except ObjectDoesNotExist:
#             return Response({"detail": "Session not found"}, status=status.HTTP_404_NOT_FOUND)

# class ForcedLogoutView(APIView):
#     # No authentication classes so the endpoint is open.

#     def post(self, request):
#         print("Forced logout request received")
#         # Optionally, try to get the token from the header (if provided)
#         token = request.META.get("HTTP_AUTHORIZATION", "").split("Bearer ")[-1]
#         if token:
#             session = UserSession.objects.filter(access_token=token).first()
#             if session:
#                 session.is_active = False
#                 session.save()
#                 print("Session marked as inactive")
#         # Clear local session regardless.
#         return Response({"detail": "Logged out successfully"}, status=status.HTTP_200_OK)


from django.core.exceptions import ObjectDoesNotExist
from rest_framework_simplejwt.tokens import RefreshToken
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from django.contrib.auth import authenticate
from rest_framework.permissions import IsAuthenticated
from .models import UserSession
from drf_yasg.utils import swagger_auto_schema
from drf_yasg import openapi
from django.utils.timezone import now
from api.custom_jwt_authentication import CookieJWTAuthentication
from rest_framework_simplejwt.authentication import JWTAuthentication

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
            # Enforce single-session: if an active session exists, deny new login.
            try:
                existing_session = UserSession.objects.get(user=user)
                if existing_session.is_active:
                    return Response({"detail": "User already has an active session."},
                                    status=status.HTTP_403_FORBIDDEN)
            except UserSession.DoesNotExist:
                pass

            # Generate JWT tokens
            refresh = RefreshToken.for_user(user)
            access_token = refresh.access_token
            access_token["username"] = user.username
            expires_at = now() + refresh.access_token.lifetime

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

            # For development, use secure=False; in production use secure=True.
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
    # Use our custom authentication that checks cookies
    authentication_classes = [CookieJWTAuthentication]

    @swagger_auto_schema(
        operation_description="Logout and remove user session",
        manual_parameters=[
            openapi.Parameter('Authorization', openapi.IN_HEADER, description="Bearer Token", type=openapi.TYPE_STRING)
        ],
        responses={200: "Logged out successfully", 401: "Unauthorized"},
    )
    def post(self, request):
        print("Logout request received")
        user = request.user if request.user.is_authenticated else None
        if user:
            try:
                print(f"Processing logout for user: {user.username}")
                session = UserSession.objects.filter(user=user).first()
                if session:
                    session.is_active = False
                    session.save()
                    print("Session marked as inactive")
                response = Response({"detail": "Logged out successfully"}, status=status.HTTP_200_OK)
                response.delete_cookie("access_token", path="/")
                response.delete_cookie("refresh_token", path="/")
                return response
            except Exception as e:
                print(f"Error during logout: {str(e)}")
                return Response({"detail": f"Error during logout: {str(e)}"},
                                status=status.HTTP_500_INTERNAL_SERVER_ERROR)
        print("User not authenticated")
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
        # Use the same path as when setting the cookies.
        response.delete_cookie("access_token", path="/")
        response.delete_cookie("refresh_token", path="/")
        return response


class GetTokenView(APIView):
    authentication_classes = [CookieJWTAuthentication]
    permission_classes = []  # or you can set AllowAny if you want

    def get(self, request):
        print("Get-token request received")

        # If the custom authentication worked, request.user should be set.
        if not request.user or not request.user.is_authenticated:
            print("User not authenticated")
            return Response({"username": None}, status=status.HTTP_200_OK)

        try:
            session = UserSession.objects.get(user=request.user, is_active=True)
            print(f"Found active session for: {request.user.username}")
            return Response({
                "username": request.user.username,
                "session_active": True
            }, status=status.HTTP_200_OK)
        except UserSession.DoesNotExist:
            print(f"No active session found for: {request.user.username}")
            return Response({"username": None}, status=status.HTTP_200_OK)



