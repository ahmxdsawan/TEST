// import { Injectable, Injector } from '@angular/core';
// import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
// import { Observable } from 'rxjs';
// import { AuthService } from '../auth.service';

// @Injectable()
// export class AuthInterceptor implements HttpInterceptor {
//   constructor(private injector: Injector) {}

//   intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
//     // With cookie–based auth, tokens are HttpOnly and not accessible via JS.
//     // Therefore, we simply pass the request along.
//     // (Optionally, you might check for a fallback value from AuthService, but typically it returns null.)
//     return next.handle(req);
//   }
// }
