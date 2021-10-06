import { User } from 'src/app/models/users/user.model';
import { SitterStoreService } from './../../../../../core/services/store/sitter-store.service';
import { SitterData } from './../../../../../models/users/sitter.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/core/services/models/user.service';
import { Role } from 'src/app/models/enums/role.model';
import { WalkInfo } from 'src/app/models/walks/walk-info.model';
import { CurrentUserStoreService } from './../../../../../core/services/store/current-user-store.service';
import { DogInfo } from './../../../../../models/dogs/dog-info.model';
import { RegularUser } from './../../../../../models/users/regular-user.model';
import { OwnerData } from 'src/app/models/users/owner.model';
import { findFirst } from 'src/app/core/services/utility/utility.model';
import { map, switchMap, tap } from 'rxjs/operators';
import { SitterService } from 'src/app/core/services/models/sitter.service';
import { OwnerService } from 'src/app/core/services/models/owner.service';
import { Observable } from 'rxjs';
import { SitterReviewCard } from 'src/app/models/reviews/sitter-review-card.model';

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
  user!: User;
  dogs?: DogInfo[];
  images?: string[];
  walks?: WalkInfo[];
  reviews?: SitterReviewCard[];
  sitterData!: Observable<SitterData>;
  ownerData!: Observable<OwnerData>;
  userData!: Observable<User>;
  mainRole?: Role;
  isCurrentUserProfile = true;
  selectedOption = Options.GALLERY;
  isSettingsOpened = false;
  setting = '';

  constructor(
    private userService: UserService,
    private sitterService: SitterService,
    private ownerService: OwnerService,
    private route: ActivatedRoute,
    private userStore: CurrentUserStoreService,
    private sitterStore: SitterStoreService,
  ) { }

  ngOnInit(): void {
    const username = this.route.snapshot.paramMap.get('username');
    if (username && username != this.userStore.regularUser.username) {
      this.isCurrentUserProfile = false;
      console.log(username);
      this.userService.getUserRole(username).subscribe(
        (res: Role) => {
          this.mainRole = res;
          switch (this.mainRole) {
            case Role.ROLE_SITTER:
              this.sitterData = this.sitterService.getSitterDataByUsername(username);
              this.sitterData.subscribe(
                res => {
                  this.images = res.images;
                  this.reviews = res.reviews;
                  this.user = res.sitter;
                }
              )
              break;

            case Role.ROLE_OWNER:
              this.ownerData = this.ownerService.getOwnerDataByUsername(username);
              this.ownerData.subscribe(res => {
                this.images = res.dogImages;
                this.dogs = res.dogs;
                this.user = res.user;
              })
              break;

            default:
              this.userData = this.userService.getUserDataParam(username);
              this.userData.subscribe(res => this.user = res);
          }
        }
      )
    } else {
      this.isCurrentUserProfile = true;
      this.mainRole = this.userStore.role;
      switch (this.mainRole) {
        case Role.ROLE_SITTER:
          this.sitterData = this.sitterService.getSitterData();
          this.sitterData.subscribe(
            res => {
              this.images = res.images;
              this.reviews = res.reviews;
              this.user = res.sitter;
            }
          )
          break;

        case Role.ROLE_OWNER:
          this.ownerData = this.ownerService.getOwnerData();
          this.ownerData.subscribe(res => {
            this.images = res.dogImages;
            this.dogs = res.dogs;
            this.user = res.user;
          })
          break;

        default:
          this.userData = this.userService.getUserData();
          this.userData.subscribe(res => this.user = res);
      }
    }
  }

  getUserInfo() {
    switch (this.mainRole) {
      case Role.ROLE_SITTER:
        return this.sitterData?.pipe(
          map(data => { return data.sitter })
        )

      case Role.ROLE_OWNER:
        return this.ownerData?.pipe(
          map(data => { return data.user })
        )
      default:
        return this.userData;
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
    this.user = event as User;
  }
}
