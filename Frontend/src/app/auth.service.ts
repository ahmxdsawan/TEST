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
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject, of, throwError } from 'rxjs';
import { map, catchError, tap, finalize } from 'rxjs/operators';
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

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    window.addEventListener('beforeunload', () => this.markSessionInactive());
    this.initializeAuth();
  }

  private initializeAuth(): void {
    const storedToken = sessionStorage.getItem('access_token');
    if (storedToken) {
      this.tokenSubject.next(storedToken);
      this.validateAndUpdateSession(storedToken);
    }
  }

  private validateAndUpdateSession(token: string): void {
    try {
      const decoded: any = jwtDecode(token);
      if (decoded.exp * 1000 > Date.now()) {
        this.usernameSubject.next(decoded.username || sessionStorage.getItem('saml_username'));
        this.scheduleAutoLogout(token);
      } else {
        this.logout();
      }
    } catch (error) {
      console.error('Token validation error:', error);
      this.logout();
    }
  }

  login(username: string, password: string): Observable<any> {
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
    window.location.href = `${this.baseUrl}/api/auth/saml/login/`;
  }

  handleSAMLRedirect(): void {
    console.log('🔍 Checking SAML redirect...');
    
    const url = new URL(window.location.href);
    const username = url.searchParams.get('username');
    const accessToken = url.searchParams.get('access');
    const error = url.searchParams.get('error');

    if (error === 'session_active') {
      console.warn('⚠️ Active session exists');
      return;
    }

    if (username && accessToken) {
      console.log('✅ SAML login successful:', username);
      this.handleAuthSuccess({ access: accessToken, username });
      window.history.replaceState({}, document.title, window.location.pathname);
      this.router.navigate(['/landing']);
    }
  }

  private handleAuthSuccess(response: AuthResponse): void {
    this.tokenSubject.next(response.access);
    this.usernameSubject.next(response.username || this.decodeUsernameFromToken(response.access));
    
    sessionStorage.setItem('access_token', response.access);
    if (response.username) {
      sessionStorage.setItem('saml_username', response.username);
    }
    
    this.scheduleAutoLogout(response.access);
  }

  private decodeUsernameFromToken(token: string): string | null {
    try {
      const decoded: any = jwtDecode(token);
      return decoded.username || null;
    } catch {
      return null;
    }
  }

  scheduleAutoLogout(token: string): void {
    try {
      const decoded: any = jwtDecode(token);
      const expirationTime = decoded.exp * 1000;
      const timeLeft = expirationTime - Date.now();

      console.log('Token expires in:', timeLeft / 1000, 'seconds');

      setTimeout(() => {
        console.log('Token expired, logging out...');
        this.logout();
      }, timeLeft);
    } catch (error) {
      console.error('Error scheduling logout:', error);
    }
  }

  logout(): void {
    console.log('Logging out...');
    const token = this.tokenSubject.getValue();

    if (token) {
      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
      
      this.http.post(`${this.baseUrl}/logout/`, {}, {
        headers,
        withCredentials: true
      }).pipe(
        finalize(() => this.clearSession())
      ).subscribe({
        next: () => console.log('✅ Backend logout successful'),
        error: error => console.error('❌ Backend logout error:', error)
      });
    } else {
      this.clearSession();
    }
  }

  private clearSession(): void {
    this.tokenSubject.next(null);
    this.usernameSubject.next(null);
    sessionStorage.clear();
    this.router.navigate(['/login']);
  }

  markSessionInactive(): void {
    const token = this.tokenSubject.getValue();
    if (token) {
      this.http.post(`${this.baseUrl}/logout-inactive/`, { token }, {
        withCredentials: true
      }).subscribe({
        next: () => console.log('Session marked inactive'),
        error: err => console.error('Failed to mark session inactive:', err)
      });
    }
  }

  restoreSession(): void {
    const storedToken = sessionStorage.getItem('access_token');
    
    if (storedToken && this.isAuthenticated()) {
      console.log('Restoring valid session...');
      this.tokenSubject.next(storedToken);
      this.validateAndUpdateSession(storedToken);
    } else {
      console.log('No valid session found');
      this.clearSession();
    }
  }

  // Public method to get username
  getDecodedUsername(): Observable<string | null> {
    return this.usernameSubject.asObservable();
  }

  // Public method to check authentication status
  isAuthenticated(): boolean {
    const token = sessionStorage.getItem('access_token');
    if (!token) return false;
  
    try {
      const decoded: any = jwtDecode(token);
      // Check if token is expired
      if (decoded.exp * 1000 < Date.now()) {
        console.log('Token is expired');
        this.clearSession();
        return false;
      }
      return true;
    } catch (error) {
      console.error('Error validating token:', error);
      this.clearSession();
      return false;
    }
  }

  // Public method to get current token
  getCurrentToken(): string | null {
    return this.tokenSubject.getValue();
  }

  
}