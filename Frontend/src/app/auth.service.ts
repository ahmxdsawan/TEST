// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable, BehaviorSubject, throwError } from 'rxjs';
// import { tap, catchError, map } from 'rxjs/operators';
// import { Router } from '@angular/router';

// interface AuthResponse {
//   detail: string;
//   username?: string;
//   expires_at?: string; 
// }

// @Injectable({
//   providedIn: 'root'
// })
// export class AuthService {
//   private baseUrl = 'http://localhost:8000';
//   private usernameSubject = new BehaviorSubject<string | null>(null);

//   constructor(private http: HttpClient, private router: Router) {
//     this.initializeAuth();
//   }

//   private initializeAuth(): void {
//     const url = new URL(window.location.href);
//     const error = url.searchParams.get('error');
//     const message = url.searchParams.get('message');

//     if (error === 'session_active') {
//         const decodedMessage = message ? decodeURIComponent(message) : 'You have an active session in another browser';
//         this.router.navigate(['/login'], {
//             queryParams: { 
//                 error: 'session_active',
//                 message: decodedMessage
//             }
//         });
//         return;
//     }

//     this.validateSessionBackend().subscribe({
//         next: (res: any) => {
//             if (res.username) {
//                 this.usernameSubject.next(res.username);
//                 this.router.navigate(['/landing']);
//             } else {
//                 this.clearSession();
//             }
//         },
//         error: (err) => {
//             console.error('Session validation failed:', err);
//             if (err.status === 403 && err.error.session_active) {
//                 this.router.navigate(['/login'], {
//                     queryParams: { error: 'session_active' }
//                 });
//             } else {
//                 this.clearSession();
//             }
//         }
//     });
// }

// login(username: string, password: string): Observable<any> {
//     return this.http.post<AuthResponse>(
//         `${this.baseUrl}/login/`,
//         { username, password },
//         { withCredentials: true }
//     ).pipe(
//         tap(response => this.handleAuthSuccess(response)),
//         catchError(error => {
//             if (error.status === 403 && error.error.detail === "User already has an active session.") {
//                 // Handle active session error
//                 this.router.navigate(['/login'], {
//                     queryParams: { error: 'session_active' }
//                 });
//             }
//             return throwError(() => error);
//         })
//     );
// }

//   loginWithMicrosoft(): void {
//     console.log('Redirecting to Microsoft SAML login...');
//     window.location.href = `${this.baseUrl}/api/auth/saml/login/`;
//   }

// handleSAMLRedirect(): void {
//     console.log('Processing SAML redirect...');
//     const url = new URL(window.location.href);
//     const username = url.searchParams.get('username');
//     const expiresAt = url.searchParams.get('expires_at');
//     const error = url.searchParams.get('error');
//     const message = url.searchParams.get('message');

//     if (error === 'session_active') {
//       console.warn('SAML redirect error: active session detected.');
//       const decodedMessage = message ? decodeURIComponent(message) : 'You have an active session in another browser';
//       console.log('Redirecting to login with error message:', decodedMessage);
      
//       // First clear any existing session
//       this.clearSession();
      
//       // Then navigate with error params
//       this.router.navigate(['/login'], {
//           queryParams: { 
//               error: 'session_active',
//               message: decodedMessage
//           },
//           replaceUrl: true  // Replace current URL to avoid navigation issues
//       }).then(() => {
//           console.log('Navigation to login complete');
//       }).catch(err => {
//           console.error('Navigation error:', err);
//       });
//       return;
//   }

//   if (username && expiresAt) {
//     console.log('SAML login successful for:', username);
//     this.handleAuthSuccess({
//         detail: 'SAML login successful',
//         username: username,
//         expires_at: expiresAt
//     });
//     window.history.replaceState({}, document.title, window.location.pathname);
//     } else {
//         console.error('Missing required SAML parameters');
//         this.router.navigate(['/login'], {
//             queryParams: { 
//                 error: 'invalid_saml',
//                 message: 'Invalid SAML response'
//             },
//             replaceUrl: true
//         });
//     }

// }
  
//   private handleAuthSuccess(response: AuthResponse): void {
//     console.log('Handling successful authentication.');
//     if (response.username) {
//       this.usernameSubject.next(response.username);
//       localStorage.setItem('saml_username', response.username);
//     }
//     if (response.expires_at) {
//       this.scheduleAutoLogout(response.expires_at);
//     }
//     this.router.navigate(['/landing']);
//   }

//   private validateSessionBackend(): Observable<any> {
//     return this.http.get<any>(`${this.baseUrl}/get-token/`, { 
//         withCredentials: true 
//     }).pipe(
//         tap(res => {
//             if (res.username) {
//                 this.usernameSubject.next(res.username);
//                 // Add this: reschedule auto-logout if expires_at is provided
//                 if (res.expires_at) {
//                     this.scheduleAutoLogout(res.expires_at);
//                 }
//             }
//         }),
//         catchError(error => {
//             console.error('Session validation error:', error);
//             this.clearSession();
//             return throwError(() => error);
//         })
//     );
// }
  

//   logout(): void {
//     console.log('Starting logout...');
//     this.http.post(`${this.baseUrl}/logout/`, {}, { withCredentials: true, observe: 'response' })
//       .pipe(
//         tap(() => console.log('Backend logout successful.')),
//         catchError(err => {
//           console.error('Error during backend logout:', err);
//           return throwError(() => err);
//         })
//       ).subscribe({
//         next: () => { this.clearSession(); },
//         error: () => { this.clearSession(); }
//       });
//   }

//   forcedLogout(): void {
//     console.log('Starting forced logout...');
    
//     // Try to mark session as inactive before clearing
//     this.http.post(`${this.baseUrl}/logout/force/`, {
//         // Send the username to help identify the session
//         username: this.usernameSubject.getValue()
//     }, { 
//         withCredentials: true, 
//         observe: 'response' 
//     }).pipe(
//         tap(() => console.log('Forced backend logout successful.')),
//         catchError(err => {
//             console.error('Error during forced logout:', err);
//             return throwError(() => err);
//         })
//     ).subscribe({
//         next: () => { this.clearSession(); },
//         error: () => { this.clearSession(); }
//     });
// }

//   private clearSession(): void {
//     localStorage.removeItem('saml_username');
//     this.usernameSubject.next(null);
//     this.router.navigate(['/login']);
//   }

//   // Schedule auto logout using the expiration time (ISO string) provided by the backend.
//   private scheduleAutoLogout(expiresAtIso: string): void {
//     // Clean up the date string by removing extra spaces and ensuring proper format
//     const cleanExpiresAt = expiresAtIso.replace(/\s+/g, '');
//     const expiresAt = new Date(cleanExpiresAt).getTime();
//     const now = Date.now();
//     const timeLeft = expiresAt - now;

//     console.log(`Auto-logout scheduled. Expires at: ${new Date(expiresAt).toISOString()}`);
//     console.log(`Time left: ${timeLeft / 1000} seconds`);

//     if (timeLeft > 0) {
//         console.log(`Setting timeout for auto logout in ${timeLeft / 1000} seconds`);
//         setTimeout(() => {
//             console.log("Session expired. Executing forced logout.");
//             this.forcedLogout();
//         }, timeLeft);
//     } else {
//         console.log("Session already expired");
//         this.forcedLogout();
//     }
// }

//   isAuthenticated(): boolean {
//     return !!this.usernameSubject.getValue();
//   }

//   getDecodedUsername(): Observable<string | null> {
//     return this.usernameSubject.asObservable();
//   }
// }

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

interface AuthResponse {
  detail: string;
  username?: string;
  expires_at?: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8000';
  private usernameSubject = new BehaviorSubject<string | null>(null);

  constructor(private http: HttpClient, private router: Router) {
    this.initializeAuth();
  }

  // Authentication initialization and validation
  private initializeAuth(): void {
    const url = new URL(window.location.href);
    const error = url.searchParams.get('error');
    const message = url.searchParams.get('message');

    if (error === 'session_active') {
      const decodedMessage = message ? decodeURIComponent(message) : 'You have an active session in another browser';
      this.router.navigate(['/login'], {
        queryParams: { error: 'session_active', message: decodedMessage }
      });
      return;
    }
    
    this.validateSessionBackend().subscribe({
      next: (res: any) => {
        if (res.username) {
          this.usernameSubject.next(res.username);
          this.router.navigate(['/landing']);
        } else {
          this.clearSession();
        }
      },
      error: (err) => {
        if (err.status === 403 && err.error.session_active) {
          this.router.navigate(['/login'], { queryParams: { error: 'session_active' } });
        } else {
          this.clearSession();
        }
      }
    });
  }

  private validateSessionBackend(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/get-token/`, { withCredentials: true })
      .pipe(
        tap(res => {
          if (res.username) {
            this.usernameSubject.next(res.username);
            if (res.expires_at) {
              this.scheduleAutoLogout(res.expires_at);
            }
          }
        }),
        catchError(error => {
          this.clearSession();
          return throwError(() => error);
        })
      );
  }

  // Login methods
  login(username: string, password: string): Observable<any> {
    return this.http.post<AuthResponse>(
      `${this.baseUrl}/login/`,
      { username, password },
      { withCredentials: true }
    ).pipe(
      tap(response => this.handleAuthSuccess(response)),
      catchError(error => {
        if (error.status === 403 && error.error.detail === "User already has an active session.") {
          this.router.navigate(['/login'], { queryParams: { error: 'session_active' } });
        }
        return throwError(() => error);
      })
    );
  }

  loginWithMicrosoft(): void {
    window.location.href = `${this.baseUrl}/api/auth/saml/login/`;
  }

  handleSAMLRedirect(): void {
    console.log('Processing SAML redirect...');
    const url = new URL(window.location.href);
    const username = url.searchParams.get('username');
    const expiresAt = url.searchParams.get('expires_at');
    const error = url.searchParams.get('error');
    const message = url.searchParams.get('message');

    if (error === 'session_active') {
      const decodedMessage = message ? decodeURIComponent(message) : 'You have an active session in another browser';
      this.clearSession();
      this.router.navigate(['/login'], {
        queryParams: { error: 'session_active', message: decodedMessage },
        replaceUrl: true
      }).then(() => console.log('Navigation to login complete'))
        .catch(err => console.error('Navigation error:', err));
      return;
    }

    if (username && expiresAt) {
      this.handleAuthSuccess({
        detail: 'SAML login successful',
        username: username,
        expires_at: expiresAt
      });
      window.history.replaceState({}, document.title, window.location.pathname);
    } else {
      this.router.navigate(['/login'], {
        queryParams: { error: 'invalid_saml', message: 'Invalid SAML response' },
        replaceUrl: true
      });
    }
  }

  // Session management
  private handleAuthSuccess(response: AuthResponse): void {
    if (response.username) {
      this.usernameSubject.next(response.username);
      localStorage.setItem('saml_username', response.username);
    }
    if (response.expires_at) {
      this.scheduleAutoLogout(response.expires_at);
    }
    this.router.navigate(['/landing']);
  }

  private scheduleAutoLogout(expiresAtIso: string): void {
    const cleanExpiresAt = expiresAtIso.replace(/\s+/g, '');
    const expiresAt = new Date(cleanExpiresAt).getTime();
    const timeLeft = expiresAt - Date.now();

    if (timeLeft > 0) {
      setTimeout(() => this.forcedLogout(), timeLeft);
    } else {
      this.forcedLogout();
    }
  }

  // Logout methods
  logout(): void {
    this.http.post(`${this.baseUrl}/logout/`, {}, { withCredentials: true, observe: 'response' })
      .pipe(
        catchError(err => throwError(() => err))
      ).subscribe({
        next: () => this.clearSession(),
        error: () => this.clearSession()
      });
  }

  forcedLogout(): void {
    this.http.post(`${this.baseUrl}/logout/force/`, 
      { username: this.usernameSubject.getValue() },
      { withCredentials: true, observe: 'response' }
    ).pipe(
      catchError(err => throwError(() => err))
    ).subscribe({
      next: () => this.clearSession(),
      error: () => this.clearSession()
    });
  }

  private clearSession(): void {
    localStorage.removeItem('saml_username');
    this.usernameSubject.next(null);
    this.router.navigate(['/login']);
  }

  // Utility methods
  isAuthenticated(): boolean {
    return !!this.usernameSubject.getValue();
  }

  getDecodedUsername(): Observable<string | null> {
    return this.usernameSubject.asObservable();
  }
}