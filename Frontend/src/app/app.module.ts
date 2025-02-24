import { NgModule, APP_INITIALIZER } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { LandingComponent } from './landing/landing.component';
import { HttpClientModule, provideHttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AuthService } from './auth.service';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzButtonModule } from 'ng-zorro-antd/button';
import {NzMenuModule} from 'ng-zorro-antd/menu';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import { NzAvatarModule } from 'ng-zorro-antd/avatar';
import { NzSwitchModule } from 'ng-zorro-antd/switch';
import { NzBreadCrumbModule } from 'ng-zorro-antd/breadcrumb';
import { NzIconModule } from 'ng-zorro-antd/icon';


registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LandingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NzLayoutModule,
    NzCardModule,
    NzButtonModule,
    NzMenuModule,
    NzLayoutModule,
    NzFormModule,
    NzInputModule,
    NzCheckboxModule,
    NzAvatarModule,
    NzSwitchModule,
    NzBreadCrumbModule,
    NzIconModule,
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: (authService: AuthService) => {
        return () => {
          // Optionally initialize AuthService if needed.
          return new Promise((resolve) => resolve(true));
        };
      },
      deps: [AuthService],
      multi: true
    },
    { provide: NZ_I18N, useValue: en_US },
    provideAnimationsAsync(),
    provideHttpClient()
    // Remove or comment out the HTTP_INTERCEPTORS provider
    // {
    //   provide: HTTP_INTERCEPTORS,
    //   useClass: AuthInterceptor,
    //   multi: true
    // },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
