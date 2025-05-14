import {Component, Input} from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import {MatSidenav} from "@angular/material/sidenav";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  @Input() drawer!: MatSidenav;

  showConnectedLinks = false;

  constructor(private router: Router, private authService: AuthService) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        const publicRoutes = ['/auth', '/login', '/register'];
        this.showConnectedLinks = this.authService.isAuthenticated() && !publicRoutes.includes(event.urlAfterRedirects);
      }
    });
  }
  logout(): void {
    this.authService.logout(); // méthode à créer si elle n'existe pas encore
    this.router.navigate(['/auth']);
  }
}
