// import { Component, OnInit } from '@angular/core';
// import { AuthService } from '../auth.service';
// import { Router } from '@angular/router';
// import { ActivatedRoute } from '@angular/router';

// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.scss']
// })
// export class LoginComponent implements OnInit {
//   username: string = '';
//   password: string = '';

//   constructor(private authService: AuthService, private router: Router, private route: ActivatedRoute) {}

//   ngOnInit() {
//     const hasSAMLParams = this.route.snapshot.queryParamMap.has("username") && 
//                           this.route.snapshot.queryParamMap.has("access");

//     if (hasSAMLParams) {
//       console.log("Detected SAML login, handling redirect...");
//       this.authService.handleSAMLRedirect();
//     } else {
//       console.log("No SAML token found, checking for existing session...");
//       this.authService.restoreSession();
//     }

//     console.log("🔍 Checking for active session...");

//     // ✅ Check if token exists in session storage and auto-redirect
//     const storedToken = sessionStorage.getItem("access_token");

//     if (storedToken) {
//       console.log("✅ Active session detected, redirecting to landing...");
//       this.router.navigate(['/landing']);
//     } else {
//       console.log("❌ No active session, user must log in.");
//     }
//   }

//   login() {
//     this.authService.login(this.username, this.password).subscribe(
//       (res) => {
//         if (res) {
//           console.log('Login successful:', res);
//           this.router.navigate(['/landing']);
//         }
//       },
//       (err) => console.error('Login error:', err)
//     );
//   }
  
//   loginWithMicrosoft() {
//     this.authService.loginWithMicrosoft();
//   }

//   // ngOnInit() {
//   //   this.authService.handleSAMLRedirect();
//   // }
  
// }

// import { Component, OnInit } from '@angular/core';
// import { AuthService } from '../auth.service';
// import { Router } from '@angular/router';

// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.scss']
// })
// export class LoginComponent implements OnInit {
//   username: string = '';
//   password: string = '';
//   loginError: string = '';

//   constructor(
//     private authService: AuthService,
//     private router: Router
//   ) {}

//   ngOnInit() {
//     if (this.authService.isAuthenticated()) {
//       this.router.navigate(['/landing']);
//     }
//   }

//   login() {
//     this.loginError = '';
//     this.authService.login(this.username, this.password).subscribe({
//       next: () => {
//         this.router.navigate(['/landing']);
//       },
//       error: (err) => {
//         console.error('Login failed:', err);
//         if (err.status === 403 && err.error.detail === "User already has an active session.") {
//           this.loginError = 'Active session detected. Please logout from your other device/browser first.';
//         } else {
//           this.loginError = 'Invalid username or password';
//         }
//       }
//     });
//   }
  
//   loginWithMicrosoft() {
//     this.authService.loginWithMicrosoft();
//   }
// }

import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  submitted: boolean = false;
  errorMessage: string = '';

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    // Check for error messages
    this.route.queryParams.subscribe(params => {
      if (params['error']) {
          if (params['error'] === 'session_active') {
              this.errorMessage = params['message'] || 'You have an active session in another browser. Please logout from there first.';
          } else if (params['error'] === 'invalid_saml') {
              this.errorMessage = params['message'] || 'SAML authentication failed';
          }
      }
  });
}

onSubmit() {
  this.submitted = true;
  this.authService.login(this.username, this.password).subscribe({
      next: () => {
          console.log('Login successful');
      },
      error: (err) => {
          console.error('Login failed', err);
          if (err.status === 403 && err.error.detail === "User already has an active session.") {
              this.errorMessage = 'You have an active session in another browser. Please logout from there first.';
          } else {
              this.errorMessage = 'Invalid username or password';
          }
      }
  });
}

  loginWithMicrosoft() {
    this.authService.loginWithMicrosoft();
  }
}
