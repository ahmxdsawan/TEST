
// import { Component, OnInit, OnDestroy } from '@angular/core';
// import { AuthService } from '../auth.service';
// import { Router } from '@angular/router';
// import { Subscription } from 'rxjs';

// @Component({
//   selector: 'app-landing',
//   templateUrl: './landing.component.html',
//   styleUrls: ['./landing.component.scss']
// })
// export class LandingComponent implements OnInit, OnDestroy {
//   username: string | null = null;
//   private subscription: Subscription = new Subscription();
//   isCollapsed = false; 
//   isDarkTheme = false;
//   constructor(public authService: AuthService, private router: Router) { }

//   ngOnInit(): void {
//     if (!this.authService.isAuthenticated()) {
//       this.router.navigate(['/login']);
//       return;
//     }
//     this.subscription = this.authService.getDecodedUsername().subscribe(username => {
//       this.username = username;
//     });
//   }

//   onMenuClick(action: string): void {
//     console.log('Menu clicked:', action);
//     // Implement your logic here, e.g., navigation or logout.
//   }

//   getUserInitials(): string {
//     if (!this.username) return 'G';
//     const parts = this.username.split('.');
//     return parts.map(n => n.charAt(0).toUpperCase()).join('');
//   }

//   ngOnDestroy(): void {
//     this.subscription.unsubscribe();
//   }

//   logout(): void {
//     this.authService.logout();
//   }

//   toggleTheme(isDark: boolean): void {
//     this.isDarkTheme = isDark;
//     // For simplicity, we'll toggle a CSS class on the body.
//     if (isDark) {
//       document.body.classList.add('dark-theme');
//     } else {
//       document.body.classList.remove('dark-theme');
//     }
//   }
// }

import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

interface MenuItem {
  title: string;
  icon: string;
  children?: string[];
}

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit, OnDestroy {
  username: string | null = null;
  isCollapsed = true;
  isDarkTheme = false;
  private subscription: Subscription = new Subscription();

  // Dynamic sidebar menu items
  menuItems: MenuItem[] = [
    { title: 'Fleet', icon: 'info-circle', children: ['Dashboard', 'KPIs', 'Availibity Dashboard', 'Events', 'CSEyeQ', 'Outage Dashboard'] },
    { title: 'Site', icon: 'environment' },
    { title: 'Operations', icon: 'tool' },
    { title: 'Compliance', icon: 'check' },
    { title: 'Reports', icon: 'file' }
  ];

  constructor(public authService: AuthService, private router: Router) {
    setTimeout(() => {
      this.isCollapsed = true;
    });
    // // Load saved theme preference
    // const savedTheme = localStorage.getItem('theme');
    // if (savedTheme) {
    //   this.isDarkTheme = savedTheme === 'dark';
    //   this.applyTheme();
    // }
  }

  // private applyTheme(): void {
  //   if (this.isDarkTheme) {
  //     document.body.classList.add('dark-theme');
  //   } else {
  //     document.body.classList.remove('dark-theme');
  //   }
  // }
  

  ngOnInit(): void {
    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['/login']);
      return;
    }
    this.subscription = this.authService.getDecodedUsername().subscribe(username => {
      this.username = username;
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  logout(): void {
    this.authService.logout();
  }

  onMenuClick(action: string): void {
    console.log('Menu clicked:', action);
    // Implement your navigation logic here.
  }

  getUserInitials(): string {
    if (!this.username) return '-';
    const parts = this.username.split('.');
    return parts.map(part => part.charAt(0).toUpperCase()).join('');
  }

  // toggleTheme(): void {
  //   this.isDarkTheme = !this.isDarkTheme;
  //   this.applyTheme();
  //   // Save theme preference
  //   localStorage.setItem('theme', this.isDarkTheme ? 'dark' : 'light');
  // }
}
