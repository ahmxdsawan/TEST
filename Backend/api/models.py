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
    

class UsersUsers(models.Model):

    username = models.CharField(max_length=255, blank=True, null=True)
    passwd = models.CharField(max_length=255, blank=True, null=True)
    fname = models.CharField(max_length=255, blank=True, null=True)
    lname = models.CharField(max_length=255, blank=True, null=True)
    notes = models.CharField(max_length=255, blank=True, null=True)
    schedule = models.CharField(max_length=255, blank=True, null=True)
    language = models.CharField(max_length=255, blank=True, null=True)
    disabled = models.IntegerField(blank=True, null=True)
    isActive = models.IntegerField(blank=True, null=True)
    jwt_secret = models.TextField(blank=True, null=True)
    user = models.OneToOneField(User, blank=True, null=True, on_delete=models.CASCADE, related_name='cseye_user')
    
    class Meta:
        db_table = 'Users_users'
    
    def __str__(self):
        return self.username
    

class UsersUserCi(models.Model):
    user = models.ForeignKey(UsersUsers, on_delete=models.CASCADE, blank=True, null=True)
    contact_type = models.CharField(max_length=255)
    contact_value = models.CharField(max_length=255, blank=True, null=True)
    sort_order = models.IntegerField(blank=True, null=True)

    class Meta:
        db_table = 'Users_user_ci'

class UsersRoles(models.Model):
    role_name = models.CharField(max_length=255)

    class Meta:
        managed = False
        db_table = 'Users_roles'

class UsersUserRl(models.Model):
    user = models.ForeignKey(UsersUsers, on_delete=models.CASCADE, blank=True, null=True)
    role = models.ForeignKey(UsersRoles, on_delete=models.CASCADE, blank=True, null=True)
    
    class Meta:
        managed = False
        db_table = 'Users_user_rl'

