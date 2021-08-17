import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/core/services/models/user.service';
import { Role } from 'src/app/models/enums/role.model';
import { RegularUser } from 'src/app/models/users/regular-user.model';
import { User } from 'src/app/models/users/user.model';

@Component({
  selector: 'ktbz-profile',
  templateUrl: './profile.component.html'
})
export class ProfileComponent implements OnInit {

  user?: User;
  mainRole?: Role;
  isCurrentUserProfile = true;

  constructor(private userService: UserService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    let username = this.route.snapshot.paramMap.get('username')
    if (username) {
      this.isCurrentUserProfile = false;
      this.userService.getUserDataParam(username).subscribe(
        (response: User | RegularUser) => {
          this.user = response as User;
          this.mainRole = this.user.user.roles.filter(r => r.role != Role.ROLE_USER)[0].role;
        }
      )
    }
    else {
      this.isCurrentUserProfile = true;
      this.userService.getUserData().subscribe(
        (response: User | RegularUser) => {
          this.user = response as User;
          this.mainRole = this.user.user.roles.filter(r => r.role != Role.ROLE_USER)[0].role;
        }
      )
    }


  }

}
