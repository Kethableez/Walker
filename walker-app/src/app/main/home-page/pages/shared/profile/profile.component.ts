import { RegularUser } from './../../../../../models/users/regular-user.model';
import { CurrentUserStoreService } from './../../../../../core/services/store/current-user-store.service';
import { PageNotFoundComponent } from './../../../../shared/page-not-found/page-not-found.component';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/core/services/models/user.service';
import { Role } from 'src/app/models/enums/role.model';
// import { RegularUser } from 'src/app/models/users/regular-user.model';
import { User } from 'src/app/models/users/user.model';
import { combineLatest } from 'rxjs';

enum Options {
  GALLERY = 'GALLERY',
  REVIEWS = 'REVIEWS',
  PETS = 'PETS'
}

@Component({
  selector: 'ktbz-profile',
  templateUrl: './profile.component.html'
})
export class ProfileComponent implements OnInit {

  user!: RegularUser;
  dogs?: any;
  walks?: any;

  mainRole?: Role;
  isCurrentUserProfile = true;
  selectedOption = Options.GALLERY;
  isSettingsOpened = false;
  setting = '';


  constructor(private userService: UserService,
    private route: ActivatedRoute,
    private userStore: CurrentUserStoreService) { }

  ngOnInit(): void {
    const username = this.route.snapshot.paramMap.get('username');

    if (username && username != this.userStore.regularUser.username) {
      this.isCurrentUserProfile = false;
      this.userService.getUserDataParam(username).subscribe(
        (response) => {
          this.mainRole = (response as User).user.roles.filter(r => r.role != Role.ROLE_USER)[0].role;

          if (this.mainRole) {
            const {dogs, walks, user} = response;
            this.walks = walks;
            this.dogs = dogs;
            this.user = user;
          }
          else this.user = response as RegularUser;
        }
      )
    }
    else {
      this.isCurrentUserProfile = true;
      this.mainRole = this.userStore.role;

      if (this.mainRole === Role.ROLE_OWNER || this.mainRole === Role.ROLE_SITTER ) {
        const {dogs, walks, user} = this.userStore.user;
        this.user = user;
        this.walks = walks;
        this.dogs = dogs;
      }
      else {
        this.user = this.userStore.regularUser;
      }

    }
  }

  openGallery() {
    this.selectedOption = Options.GALLERY;
  }

  openReviews() {
    this.selectedOption = Options.REVIEWS;
  }

  openPets() {
    this.selectedOption = Options.PETS;
  }

  openEditForm(setting: string) {
    this.toggleSettings(true);
    this.setting = setting;
  }

  toggleSettings(val: boolean) {
    this.isSettingsOpened = val;
  }

  updateData(event: any) {
    console.log(this.user);
    console.log(event);
    this.user = event as RegularUser;
  }

}
