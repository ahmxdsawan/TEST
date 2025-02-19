from django.urls import path
from .views import microsoft_login, acs

urlpatterns = [
    path('login/', microsoft_login, name='microsoft-login'),
    path('acs/', acs, name='saml-acs'),
]
