import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Roles } from 'src/app/_models/roles';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'ktbz-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
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
