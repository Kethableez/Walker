import { Component, OnInit } from '@angular/core';
import { ServerResponse } from '../../models/server-response.model';
import { TokenStorageService } from 'src/app/core/services/auth/token-storage.service';
import { UserService } from 'src/app/core/services/models/user.service';
import { User } from 'src/app/models/user.model';
import { Owner } from 'src/app/models/owner.model';
import { Sitter } from 'src/app/models/sitter.model';

@Component({
  selector: 'ktbz-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  isLoggedIn = false;
  currentUser: any;
  userData?: User | Owner | Sitter;
  response?: ServerResponse;

  constructor(private token: TokenStorageService,
    private userService: UserService) { }

  ngOnInit(): void {
    if (this.token.getToken()) {
      this.isLoggedIn = true;
      this.currentUser = this.token.getUser();

      this.userService.getUserData().subscribe(
        (response: User | Owner | Sitter) => {
          this.userData = response;
          console.log(this.userData);
        }
      )
    }
  }
}
