import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/core/services/models/user.service';
import { RegularUser } from 'src/app/models/users/regular-user.model';
import { User } from 'src/app/models/users/user.model';

@Component({
  selector: 'ktbz-profile',
  templateUrl: './profile.component.html'
})
export class ProfileComponent implements OnInit {

  user?: User;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    let user: User | RegularUser;
    this.userService.getUserData().subscribe(
      (response: User | RegularUser) => {
        user = response;
        this.user = user as User;
      }
    )
  }

}
