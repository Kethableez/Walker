import { Component, OnInit } from '@angular/core';
import { ServerResponse } from '../../models/server-response.model';
import { TokenStorageService } from 'src/app/core/services/auth/token-storage.service';
import { UserService } from 'src/app/core/services/models/user.service';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'ktbz-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  isLoggedIn = false;
  currentUser: any;
  userData?: User;
  response: ServerResponse = {
    'message': ' ',
    'code': 200
  }

  constructor(private token: TokenStorageService,
    private userService: UserService) { }

  ngOnInit(): void {
    if (this.token.getToken()) {
      this.isLoggedIn = true;
      this.currentUser = this.token.getUser();

      this.userService.getUserData().subscribe(
        (response: any) => {
          this.userData = response.user;
          this.response.message = response.user.username;
          console.log(this.response)
          console.log(this.userData);
        }
      )
    }
  }


}
