// import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
// import { AuthService } from '../auth.service';

// @Component({
//   selector: 'app-landing',
//   templateUrl: './landing.component.html',
//   styleUrls: ['./landing.component.scss']
// })
// export class LandingComponent implements OnInit {
//   username: string | null = 'Guest';
//   email: string | null = '';

//   constructor(public authService: AuthService, private cdr: ChangeDetectorRef) {}

//   ngOnInit() {
//     this.authService.getDecodedUsername().subscribe(username => {
//       if (username) {
//         this.username = username;  // Correctly set username
//       } else {
//         this.username = 'Guest';  // Fallback
//       }
//       console.log("Username in LandingComponent:", this.username);
//       this.cdr.detectChanges();  // Force UI update
//     });
//   }
// }

import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit, OnDestroy {
  username: string | null = null;
  private subscription: Subscription = new Subscription();

  constructor(
    public authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['/login']);
      return;
    }

    this.subscription = this.authService.getDecodedUsername().subscribe(
      username => {
        this.username = username;
        console.log('Current user:', username);
      }
    );
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  logout() {
    this.authService.logout();
  }
}