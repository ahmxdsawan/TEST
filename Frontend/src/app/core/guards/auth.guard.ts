import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth.service';  // Update this path based on your structure

@Injectable({
  providedIn: 'root'
})
export class AuthGuard {
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  canActivate(): boolean {
    if (this.authService.isAuthenticated()) {
      return true;
    }

    this.router.navigate(['/login']);
    return false;
  }
}