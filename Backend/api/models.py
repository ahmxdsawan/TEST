from django.db import models
from django.contrib.auth.models import User
from django.utils.timezone import now
from datetime import timedelta

#  Store active user sessions in the database
class UserSession(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)  # One session per user
    access_token = models.TextField()  # Store JWT Access Token
    refresh_token = models.TextField()  # Store JWT Refresh Token
    expires_at = models.DateTimeField(default=now)  # Token Expiration Time
    created_at = models.DateTimeField(auto_now_add=True)  # Session creation timestamp
    is_active = models.BooleanField(default=True)  # Session status

    def __str__(self):
        return f"Session for {self.user.username} (Expires: {self.expires_at})"
