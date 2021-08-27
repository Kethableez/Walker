import { CurrentUserStoreService } from './../services/store/current-user-store.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenStorageService } from '../services/auth/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private token: TokenStorageService, private router: Router, private userStore: CurrentUserStoreService) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      let url: string = state.url;
      return this.checkUserLogin(route, url);
  }

  checkUserLogin(route: ActivatedRouteSnapshot, url: any): boolean {
    if (this.token.getToken() != null) {
      const userRole = this.userStore.roles;
      if (!userRole.includes(route.data.role)) {
        this.router.navigate(['/home/dashboard']);
        return false;
      }
      return true;
    }

    this.router.navigate(['/start']);
    return false;
  }
}
