import { Component, OnInit } from '@angular/core';
import { ActionResponse } from '../../models/action-response.model';
import { TokenStorageService } from 'src/app/core/services/auth/token-storage.service';
import { UserService } from 'src/app/core/services/models/user.service';
import { RegularUser } from 'src/app/models/users/regular-user.model';
import { User } from 'src/app/models/users/user.model';


@Component({
  selector: 'ktbz-home-page',
  templateUrl: './home-page.component.html'
})
export class HomePageComponent implements OnInit {

  isLoggedIn = false;
  currentUser: any;
  userData?: User;
  response?: ActionResponse;

  constructor(private token: TokenStorageService,
    private userService: UserService) { }

  ngOnInit(): void {
    if (this.token.getToken()) {
      this.isLoggedIn = true;
      this.currentUser = this.token.getUser();

      this.userService.getUserData().subscribe(
        (response: User | RegularUser) => {
          this.userData = response as User;
          console.log(this.userData.user.avatar);
        }
      )
    }
  }
}
