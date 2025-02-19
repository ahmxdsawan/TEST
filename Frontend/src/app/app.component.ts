// import { Component, OnInit } from '@angular/core';
// import { AuthService } from './auth.service';

// @Component({
//   selector: 'app-root',
//   templateUrl: './app.component.html',
//   styleUrl: './app.component.scss'
// })
// export class AppComponent implements OnInit {
//   title = 'cseyeFe';
//   constructor(private authService: AuthService) {}

//   ngOnInit() {
//     // this.authService.scheduleAutoLogout(); // Ensure logout is scheduled when app starts
//     this.authService.handleSAMLRedirect(); // Handle SAML login on page load
//     this.authService.restoreSession(); // Restore session if user is already logged in
//   }
// }

import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'cseyeFe';
  
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    // Check if this is a SAML redirect
    const url = new URL(window.location.href);
    const username = url.searchParams.get('username');

    if (username) {
      console.log('SAML redirect detected, handling...');
      this.authService.handleSAMLRedirect();
    } else {
      // Only check authentication if not a SAML redirect
      console.log('Not a SAML redirect, checking authentication...');
      if (this.authService.isAuthenticated()) {
        console.log('User is authenticated, going to landing');
        this.router.navigate(['/landing']);
      } else {
        console.log('User is not authenticated, going to login');
        this.router.navigate(['/login']);
      }
    }
  }
}