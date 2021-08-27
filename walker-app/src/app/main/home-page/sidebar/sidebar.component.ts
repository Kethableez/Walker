import { CurrentUserStoreService } from './../../../core/services/store/current-user-store.service';
import { Router } from '@angular/router';
import { Component, Input, OnInit, ChangeDetectorRef, OnChanges, SimpleChanges, DoCheck } from '@angular/core';
import { TokenStorageService } from 'src/app/core/services/auth/token-storage.service';

export enum Roles {
  ADMIN = 'ROLE_ADMIN',
  USER = 'ROLE_USER',
  OWNER = 'ROLE_OWNER',
  SITTER = 'ROLE_SITTER'
}

@Component({
  selector: 'ktbz-sidebar',
  templateUrl: './sidebar.component.html'
})
export class SidebarComponent implements OnInit, OnChanges, DoCheck {

  @Input()
  roles!: string[];

  // @Input()
  avatar = this.userStore.regularUser.avatar;
  mainRole = this.userStore.role;

  constructor(
    private token: TokenStorageService,
    private userStore: CurrentUserStoreService,
    private router: Router,
    private ref: ChangeDetectorRef
    ) { }

  ngOnInit(): void {
    console.log(this.mainRole);
  }

  ngOnChanges(changes: SimpleChanges) {
  }

  ngDoCheck() {
    if (this.avatar !== this.userStore.regularUser.avatar) this.avatar = this.userStore.regularUser.avatar;
  }



  logoutUser(): void {
    this.token.signOut();
    this.userStore.removeUser();
    this.router.navigate(['start']);
  }

}
