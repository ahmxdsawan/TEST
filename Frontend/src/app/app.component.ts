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
    // First, check if we're on a SAML redirect
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('username') && urlParams.has('access')) {
      console.log('Handling SAML redirect...');
      this.authService.handleSAMLRedirect();
      return;
    }

    // If not a SAML redirect, check for existing session
    if (this.authService.isAuthenticated()) {
      console.log('Valid session found, redirecting to landing...');
      this.router.navigate(['/landing']);
    } else {
      console.log('No valid session found, redirecting to login...');
      this.router.navigate(['/login']);
    }
  }
}