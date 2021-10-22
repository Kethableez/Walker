import { User } from 'src/app/models/users/user.model';
import { SitterStoreService } from './../../../../../core/services/store/sitter-store.service';
import { SitterData } from './../../../../../models/users/sitter.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/core/services/models/user.service';
import { Role } from 'src/app/models/enums/role.model';
import { WalkInfo } from 'src/app/models/walks/walk-info.model';
import { CurrentUserStoreService } from './../../../../../core/services/store/current-user-store.service';
import { RegularUser } from './../../../../../models/users/regular-user.model';
import { OwnerData } from 'src/app/models/users/owner.model';
import { exists, findFirst } from 'src/app/core/services/utility/utility.model';
import { map, switchMap, tap } from 'rxjs/operators';
import { SitterService } from 'src/app/core/services/models/sitter.service';
import { OwnerService } from 'src/app/core/services/models/owner.service';
import { Observable, of } from 'rxjs';
import { SitterReviewCard } from 'src/app/models/reviews/sitter-review-card.model';
import { DogCard } from 'src/app/models/dogs/dog-card.model';

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

  username = this.prepareProfileView();
  userData = this.userService.getUserData(this.username);
  mainRole = this.userService.getUserRole(this.username);

  user!: User;
  dogs?: DogCard[];
  images?: string[];
  walks?: WalkInfo[];
  reviews?: SitterReviewCard[];
  sitterData!: Observable<SitterData>;
  ownerData!: Observable<OwnerData>;
  isCurrentUserProfile = true;
  selectedOption = Options.GALLERY;
  isSettingsOpened = false;
  isSwitchBoxDisplayed?: boolean;
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
    this.userData.subscribe(res => console.log(res));
    this.mainRole.subscribe((role: Role) => {
      switch (role) {
        case Role.ROLE_SITTER:
          this.isSwitchBoxDisplayed = true;
          this.sitterData = this.sitterService.getSitterData(this.username);
          this.sitterData.subscribe((sitter: SitterData) => {
            this.images = sitter.images;
          }
          )
          break;

        case Role.ROLE_OWNER:
          this.isSwitchBoxDisplayed = true;
          this.ownerData = this.ownerService.getOwnerData(this.username);
          this.ownerData.subscribe((owner: OwnerData) => {
            this.images = owner.dogImages;
          })
          break;

        default:
          this.isSwitchBoxDisplayed = false;
          break;
      }
    })
  }


  private prepareProfileView() {
    const username = this.route.snapshot.paramMap.get('username');
    this.isCurrentUserProfile = username ? false : true;
    return username ? username : undefined;
  }

  getImageList(): Observable<string[]> | null {
    if (this.sitterData) return this.sitterData.pipe(
      map(data => { return data.images })
    );
    else if (this.ownerData) return this.ownerData.pipe(
      map(data => { return data.dogImages })
    );
    else return null;
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
