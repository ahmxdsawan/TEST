// import { NgModule } from '@angular/core';
// import { RouterModule, Routes } from '@angular/router';
// import { LoginComponent } from './login/login.component';
// import { LandingComponent } from './landing/landing.component';

// const routes: Routes = [
//   { path: '', redirectTo: '/login', pathMatch: 'full' },
//   { path: 'login', component: LoginComponent },
//   { path: 'landing', component: LandingComponent },
// ];

// @NgModule({
//   imports: [RouterModule.forRoot(routes)],
//   exports: [RouterModule]
// })
// export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LandingComponent } from './landing/landing.component';
import { AuthGuard } from '../app/core/guards/auth.guard';  // Updated import path

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { 
    path: 'landing', 
    component: LandingComponent,
    canActivate: [AuthGuard]
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }