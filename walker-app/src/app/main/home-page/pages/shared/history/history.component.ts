import { PastWalkInfo } from './../../../../../models/walks/past-walk-info.model';
import { CurrentUserStoreService } from './../../../../../core/services/store/current-user-store.service';
import { WalkCard } from './../../../../../models/walks/walk-card.model';
import { SitterService } from './../../../../../core/services/models/sitter.service';
import { Role } from './../../../../../models/enums/role.model';
import { TokenStorageService } from 'src/app/core/services/auth/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { OwnerService } from 'src/app/core/services/models/owner.service';
import { PastWalkCard } from 'src/app/models/walks/past-walk-card.model';

@Component({
  selector: 'ktbz-history',
  templateUrl: './history.component.html',
})
export class HistoryComponent implements OnInit {
  constructor(
    // private tokenService: TokenStorageService,
    private userStore: CurrentUserStoreService,
    private sitterService: SitterService,
    private ownerService: OwnerService
  ) {}

  roleView = this.userStore.role;
  walkHistory: WalkCard[] = [];
  reviewedWalkId = '';
  setting = '';
  isSettingsOpened = false;

  ngOnInit(): void {
    if (this.roleView === Role.ROLE_SITTER) {
      this.sitterService.getHistory().subscribe(
        (res) => {
          (this.walkHistory = res);
          console.log(res);
        }
      );
    }

    else if (this.roleView === Role.ROLE_OWNER) {
      this.ownerService.getHistory().subscribe(
        (res) => {
          this.walkHistory = res;
          console.log('hist', res);
        }
      )
    }
  }

  toggleSettings(val: boolean) {
    this.isSettingsOpened = val;
  }

  openReviewForm(setting: string, walkId: string) {
    this.setting = setting;
    this.reviewedWalkId = walkId;
    console.log(this.reviewedWalkId, this.setting);
    this.toggleSettings(true);
  }
}
