from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/auth/saml/', include('api.auth.saml.urls')),  #  Ensure SAML URLs are included
]