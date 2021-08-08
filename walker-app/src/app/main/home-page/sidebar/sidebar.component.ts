import { Router } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';
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
export class SidebarComponent implements OnInit {

  @Input()
  roles!: string[];

  mainRole!: string;

  constructor(private token: TokenStorageService,
    private router: Router) { }

  ngOnInit(): void {
    this.roles.forEach(role => {
      switch (role) {
        case 'ROLE_ADMIN':
          this.mainRole = Roles.ADMIN
          break;

        case 'ROLE_OWNER':
          this.mainRole = Roles.OWNER
          break;

        case 'ROLE_SITTER':
          this.mainRole = Roles.SITTER
          break;
      }
    })
  }


  logoutUser(): void {
    this.token.signOut();
    this.router.navigate(['start']);
  }

}
