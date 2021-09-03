import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/core/services/models/user.service';
import { Role } from 'src/app/models/enums/role.model';
import { WalkInfo } from 'src/app/models/walks/walk-info.model';
import { CurrentUserStoreService } from './../../../../../core/services/store/current-user-store.service';
import { DogInfo } from './../../../../../models/dogs/dog-info.model';
import { RegularUser } from './../../../../../models/users/regular-user.model';

enum Options {
  GALLERY = 'GALLERY',
  REVIEWS = 'REVIEWS',
  PETS = 'PETS',
}

@Component({
  selector: 'ktbz-profile',
  templateUrl: './profile.component.html',
})
export class ProfileComponent implements OnInit {
  user!: RegularUser;
  dogs?: DogInfo[];
  walks?: WalkInfo[];

  mainRole?: Role;
  isCurrentUserProfile = true;
  selectedOption = Options.GALLERY;
  isSettingsOpened = false;
  setting = '';

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private userStore: CurrentUserStoreService
  ) {}

  ngOnInit(): void {
    const username = this.route.snapshot.paramMap.get('username');
    if (username && username != this.userStore.regularUser.username) {
      this.isCurrentUserProfile = false;
      this.userService
        .getUserDataParam(username)
        .subscribe((response: RegularUser) => {
          this.user = response,
          this.mainRole = this.user.roles.filter(role => role != Role.ROLE_USER)[0];
        });
    } else {
      this.isCurrentUserProfile = true;
      this.mainRole = this.userStore.role;
      this.user = this.userStore.regularUser;
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
    this.user = event as RegularUser;
  }
}
