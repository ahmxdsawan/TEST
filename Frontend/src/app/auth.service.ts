// import { Injectable, HostListener } from '@angular/core';
// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Observable, of } from 'rxjs';
// import { map, catchError } from 'rxjs/operators';
// import { Router } from '@angular/router';
// import { jwtDecode } from 'jwt-decode';

// @Injectable({
//   providedIn: 'root'
// })
// export class AuthService {
//   private baseUrl = 'http://127.0.0.1:8000';
//   private token: string | null = null;

//   constructor(private http: HttpClient, private router: Router) {
//     window.addEventListener("beforeunload", () => this.markSessionInactive());
//   }

//   markSessionInactive() {
//     if (this.token) {
//       this.http.post(`${this.baseUrl}/logout-inactive/`, { token: this.token }, { withCredentials: true })
//         .subscribe(() => console.log("Session marked as inactive"), err => console.error("Failed to mark session inactive", err));
//     }
//   }

//   //  Login: Store token after successful login
//   login(username: string, password: string): Observable<any> {
//     return this.http.post<any>(`${this.baseUrl}/login/`, { username, password }, { withCredentials: true }).pipe(
//       map((res) => {
//         console.log("Login Response:", res);
//         this.storeToken(res.access, res.refresh, username);
//         return res;
//       }),
//       catchError((err) => {
//         console.error("Login error:", err);
//         return of(null);
//       })
//     );
//   }

//   //  SAML Login
//   loginWithMicrosoft() {
//     window.location.href = `${this.baseUrl}/api/auth/saml/login/`;
//   }

//   //  Handle SAML Redirect & Store Token
//   handleSAMLRedirect() {
//     console.log("🚀 Checking SAML Redirect...");
  
//     // ✅ Check if session token already exists in sessionStorage
//     const storedToken = sessionStorage.getItem("access_token");
  
//     if (storedToken) {
//       console.log("✅ Active session detected, redirecting to landing...");
//       this.token = storedToken;
//       this.router.navigate(['/landing']);
//       return;
//     }
  
//     // ✅ If no session token, process SAML response from URL
//     const url = new URL(window.location.href);
//     const username = url.searchParams.get("username");
//     const accessToken = url.searchParams.get("access");
  
//     console.log("🔹 Extracted from URL:", { username, accessToken });
  
//     if (username && accessToken) {
//       console.log("✅ SAML Login Success:", username);
  
//       this.token = accessToken; // Store in memory
//       sessionStorage.setItem("access_token", accessToken); // Persist in sessionStorage
//       sessionStorage.setItem("saml_username", username); // Store username
  
//       this.scheduleAutoLogout(accessToken);
  
//       // ✅ Remove SAML params from URL
//       window.history.replaceState({}, document.title, window.location.pathname);
  
//       this.router.navigate(['/landing']); // Redirect to landing page
//     } else {
//       console.warn("⚠️ No active session or SAML response found.");
//     }
//   }
  

//   //  Store token in session storage and memory
//   private storeToken(accessToken: string, refreshToken: string | null, username: string) {
//     this.token = accessToken;
//     sessionStorage.setItem("access_token", accessToken);
//     sessionStorage.setItem("saml_username", username);
    
//     if (refreshToken) {
//       sessionStorage.setItem("refresh_token", refreshToken);
//     }

//     this.scheduleAutoLogout(accessToken);
//   }

//   //  Restore session after page refresh
//   restoreSession() {
//     const storedToken = sessionStorage.getItem("access_token");
    
//     if (storedToken) {
//       console.log("🔄 Restoring session with stored token...");
//       this.token = storedToken;
//       this.router.navigate(['/landing']); // ✅ Redirect to landing immediately
//     } else {
//       console.warn("⚠️ No stored session found.");
//     }
//   }
  

//   //  Auto logout when token expires
//   scheduleAutoLogout(token: string) {
//     const decoded: any = jwtDecode(token);
//     const expirationTime = decoded.exp * 1000; // Convert to milliseconds
//     const timeLeft = expirationTime - Date.now();

//     console.log("Token expires in:", timeLeft / 1000, "seconds");

//     setTimeout(() => {
//       this.logout();
//     }, timeLeft);
//   }

//   //  Logout: Remove token and session
//   logout() {
//     console.log("Logging out...");

//     const storedToken = sessionStorage.getItem("access_token");
    
//     if (storedToken) {
//       const headers = new HttpHeaders({
//         'Authorization': `Bearer ${storedToken}`
//       });

//       this.http.post(`${this.baseUrl}/logout/`, {}, { headers, withCredentials: true }).subscribe(
//         () => console.log(" Backend logout successful"),
//         (error) => console.error("Backend logout error:", error)
//       );
//     }

//     sessionStorage.clear();
//     this.token = null;
//     this.router.navigate(['/login']);
//   }

//   //  Fetch token from backend if needed
//   getToken(): Observable<string | null> {
//     if (!this.token) return of(null);

//     const headers = new HttpHeaders({
//       'Authorization': `Bearer ${this.token}`
//     });

//     return this.http.get<{ token: string }>(`${this.baseUrl}/get-token/`, { headers, withCredentials: true }).pipe(
//       map(response => response.token || null),
//       catchError(() => of(null))
//     );
//   }

//   //  Decode JWT token to get username
//   getDecodedUsername(): Observable<string | null> {
//     let token = this.token || sessionStorage.getItem("access_token");

//     if (!token) return of(null);

//     try {
//       const decoded: any = jwtDecode(token);
//       return of(decoded.username || null);
//     } catch (error) {
//       console.error("Error decoding token:", error);
//       return of(null);
//     }
//   }
// }
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpBackend } from '@angular/common/http';
import { Observable, BehaviorSubject, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

interface AuthResponse {
  access: string;
  refresh?: string;
  username?: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://127.0.0.1:8000';
  private tokenSubject = new BehaviorSubject<string | null>(null);
  private usernameSubject = new BehaviorSubject<string | null>(null);

  // HttpClient without interceptors for refresh/validation calls.
  private httpClientNoInterceptor: HttpClient;

  constructor(
    private http: HttpClient,
    private httpBackend: HttpBackend,
    private router: Router
  ) {
    this.httpClientNoInterceptor = new HttpClient(this.httpBackend);
    this.initializeAuth();
  }

  private initializeAuth(): void {
    let storedToken = sessionStorage.getItem('access_token');
    let storedUsername = sessionStorage.getItem('saml_username');

    // Restore tokens from localStorage if sessionStorage is empty.
    if (!storedToken && localStorage.getItem('access_token')) {
      storedToken = localStorage.getItem('access_token');
      storedUsername = localStorage.getItem('saml_username');
      sessionStorage.setItem('access_token', storedToken!);
      sessionStorage.setItem('saml_username', storedUsername!);
    }

    if (storedToken) {
      console.log('Token found in storage. Validating locally and with backend...');
      this.tokenSubject.next(storedToken);
      this.usernameSubject.next(storedUsername);
      this.validateAndUpdateSession(storedToken);
      this.validateSessionBackend().subscribe({
        next: () => {
          console.log('Session validated by backend.');
          this.router.navigate(['/landing']);
        },
        error: (err) => {
          console.error('Backend session validation failed:', err);
          this.clearSession();
        }
      });
    } else {
      console.log('No token found in storage.');
    }
  }

  // Validate token locally and schedule auto logout.
  private validateAndUpdateSession(token: string): void {
    try {
      const decoded: any = jwtDecode(token);
      if (decoded.exp * 1000 > Date.now()) {
        console.log('Local token validation succeeded.');
        this.usernameSubject.next(decoded.username || sessionStorage.getItem('saml_username'));
        // Instead of prompting, schedule an automatic logout when the token expires.
        this.scheduleAutoLogout(token);
      } else {
        console.log('Local token expired.');
        this.logout();
      }
    } catch (error) {
      console.error('Error during local token validation:', error);
      this.logout();
    }
  }

  // Validate the session with the backend.
  private validateSessionBackend(): Observable<any> {
    console.log('Validating session with backend...');
    const token = this.getCurrentToken();
    let headers = new HttpHeaders();
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`);
    }
    return this.httpClientNoInterceptor.get(`${this.baseUrl}/get-token/`, { headers, withCredentials: true }).pipe(
      tap((res) => console.log('Backend session validation response:', res)),
      catchError(error => {
        console.error('Error from backend session validation:', error);
        return throwError(() => error);
      })
    );
  }

  login(username: string, password: string): Observable<any> {
    console.log('Attempting login for:', username);
    return this.http.post<AuthResponse>(`${this.baseUrl}/login/`, { username, password }, {
      withCredentials: true
    }).pipe(
      tap(response => this.handleAuthSuccess(response)),
      catchError(error => {
        console.error('Login error:', error);
        return throwError(() => error);
      })
    );
  }

  loginWithMicrosoft(): void {
    console.log('Redirecting to Microsoft SAML login...');
    window.location.href = `${this.baseUrl}/api/auth/saml/login/`;
  }

  handleSAMLRedirect(): void {
    console.log('Processing SAML redirect...');
    const url = new URL(window.location.href);
    const username = url.searchParams.get('username');
    const accessToken = url.searchParams.get('access');
    const error = url.searchParams.get('error');

    if (error === 'session_active') {
      console.warn('SAML redirect error: active session detected.');
      this.router.navigate(['/login']);
      return;
    }

    if (username && accessToken) {
      console.log('SAML login successful for:', username);
      this.handleAuthSuccess({ access: accessToken, username });
      window.history.replaceState({}, document.title, window.location.pathname);
      this.router.navigate(['/landing']);
    }
  }

  // Save tokens and schedule auto logout.
  private handleAuthSuccess(response: AuthResponse): void {
    console.log('Handling successful authentication.');
    this.tokenSubject.next(response.access);
    const username = response.username || this.decodeUsernameFromToken(response.access);
    this.usernameSubject.next(username);

    sessionStorage.setItem('access_token', response.access);
    sessionStorage.setItem('saml_username', username || '');
    localStorage.setItem('access_token', response.access);
    localStorage.setItem('saml_username', username || '');

    if (response.refresh) {
      sessionStorage.setItem('refresh_token', response.refresh);
      localStorage.setItem('refresh_token', response.refresh);
    }

    // Schedule automatic logout when the token expires.
    this.scheduleAutoLogout(response.access);
  }

  private decodeUsernameFromToken(token: string): string | null {
    try {
      const decoded: any = jwtDecode(token);
      return decoded.username || null;
    } catch (error) {
      console.error('Error decoding token:', error);
      return null;
    }
  }

  // Schedules automatic logout when the token expires.
  scheduleAutoLogout(token: string): void {
    try {
      const decoded: any = jwtDecode(token);
      const expirationTime = decoded.exp * 1000; // Convert expiration time to milliseconds
      const timeLeft = expirationTime - Date.now();
      console.log(`Token expires in ${timeLeft / 1000} seconds. Scheduling auto logout.`);
      setTimeout(() => {
        console.log("Token expired. Logging out automatically.");
        this.logout();
      }, timeLeft);
    } catch (e) {
      console.error('Error scheduling auto logout:', e);
      this.logout();
    }
  }

  /**
   * Calls the refresh endpoint to obtain a new access token.
   */
  refreshToken(): Observable<any> {
    const storedRefreshToken = sessionStorage.getItem('refresh_token') || localStorage.getItem('refresh_token');
    if (!storedRefreshToken) {
      return throwError(() => new Error("No refresh token available"));
    }
    return this.http.post(`${this.baseUrl}/token/refresh/`, { refresh: storedRefreshToken }, {
      withCredentials: true
    }).pipe(
      tap((response: any) => {
        const newAccess = response.access;
        const newRefresh = response.refresh; // if provided by the backend
        this.tokenSubject.next(newAccess);
        sessionStorage.setItem('access_token', newAccess);
        localStorage.setItem('access_token', newAccess);
        if (newRefresh) {
          sessionStorage.setItem('refresh_token', newRefresh);
          localStorage.setItem('refresh_token', newRefresh);
        }
        // Reschedule auto logout for the new token.
        this.scheduleAutoLogout(newAccess);
      }),
      catchError(error => {
        console.error('Error refreshing token:', error);
        return throwError(() => error);
      })
    );
  }

  logout(isForced: boolean = false): void {
    console.log('Starting logout...');
    const token = this.getCurrentToken();
  
    // Check if the token is expired.
    let tokenExpired = false;
    if (token) {
      try {
        const decoded: any = jwtDecode(token);
        tokenExpired = decoded.exp * 1000 < Date.now();
      } catch (e) {
        tokenExpired = true;
      }
    }
  
    // If token exists, is valid, and this is not a forced logout, call the normal endpoint.
    if (token && !tokenExpired && !isForced) {
      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
      this.http.post(`${this.baseUrl}/logout/`, {}, {
        headers,
        withCredentials: true,
        observe: 'response'
      }).pipe(
        tap(() => console.log('Backend logout successful.')),
        catchError(err => {
          console.error('Error during backend logout:', err);
          return throwError(() => err);
        })
      ).subscribe({
        next: () => { this.clearSession(); },
        error: () => { this.clearSession(); }
      });
    } else {
      // If the token is expired or this is a forced logout, call a forced logout endpoint.
      // (Make sure your backend provides an endpoint like /logout/force/ that doesn't require a valid token.)
      console.log('Token is expired or forced logout requested. Calling forced logout.');
      this.http.post(`${this.baseUrl}/logout/force/`, {}, {
        withCredentials: true
      }).pipe(
        tap(() => console.log('Forced backend logout successful.')),
        catchError(err => {
          console.error('Error during forced logout:', err);
          // Even if the forced call fails, clear the session.
          return throwError(() => err);
        })
      ).subscribe({
        next: () => { this.clearSession(); },
        error: () => { this.clearSession(); }
      });
    }
  }

  private clearSession(): void {
    console.log('Clearing session data.');
    this.tokenSubject.next(null);
    this.usernameSubject.next(null);
    sessionStorage.clear();
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  markSessionInactive(): void {
    const token = this.tokenSubject.getValue() || sessionStorage.getItem('access_token');
    if (token) {
      this.http.post(`${this.baseUrl}/logout-inactive/`, { token }, { withCredentials: true })
        .subscribe({
          next: () => console.log('Session marked as inactive on backend'),
          error: (err) => console.error('Error marking session inactive:', err)
        });
    }
  }

  isAuthenticated(): boolean {
    const token = sessionStorage.getItem('access_token');
    if (!token) return false;
    try {
      const decoded: any = jwtDecode(token);
      if (decoded.exp * 1000 < Date.now()) {
        this.clearSession();
        return false;
      }
      return true;
    } catch (error) {
      this.clearSession();
      return false;
    }
  }

  getDecodedUsername(): Observable<string | null> {
    return this.usernameSubject.asObservable();
  }

  getCurrentToken(): string | null {
    return this.tokenSubject.getValue() || sessionStorage.getItem('access_token');
  }
}

