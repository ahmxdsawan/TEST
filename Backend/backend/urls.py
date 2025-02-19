from django.contrib import admin
from django.urls import path, include
# from api.views import LoginView, LogoutView, GetTokenView, MarkInactiveView, ForcedLogoutView
from api.views import LoginView, LogoutView, ForcedLogoutView, GetTokenView

from rest_framework import permissions
from drf_yasg.views import get_schema_view
from drf_yasg import openapi
from django.urls import path, include
from rest_framework_simplejwt.views import TokenRefreshView

# from backend.api.views import SAMLLoginView, SAMLLogoutView

# Define schema view
schema_view = get_schema_view(
    openapi.Info(
        title="Your API",
        default_version='v1',
        description="Description of your API",
        terms_of_service="https://www.google.com/policies/terms/",
        contact=openapi.Contact(email="contact@yourapi.com"),
        license=openapi.License(name="MIT License"),
    ),
    public=True,
)

urlpatterns = [
    path('admin/', admin.site.urls),
    path('login/', LoginView.as_view(), name='login'),
    path('logout/', LogoutView.as_view(), name='logout'),
    path('get-token/', GetTokenView.as_view(), name='get-token'),
    path('swagger/', schema_view.with_ui('swagger', cache_timeout=0), name='schema-swagger-ui'),
    path('accounts/', include('allauth.urls')),
    # path('logout-inactive/', MarkInactiveView.as_view(), name='logout-inactive'),
    path('logout/force/', ForcedLogoutView.as_view(), name='forced_logout'),
    path('token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),

    path('saml/', include('api.auth.saml.urls')),
    path('api/auth/saml/', include('api.auth.saml.urls')),
]
