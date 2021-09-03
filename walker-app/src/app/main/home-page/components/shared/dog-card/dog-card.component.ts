import { SitterStoreService } from './../../../../../core/services/store/sitter-store.service';
import { CurrentUserStoreService } from './../../../../../core/services/store/current-user-store.service';
import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { TokenStorageService } from 'src/app/core/services/auth/token-storage.service';
import { UserService } from 'src/app/core/services/models/user.service';
import { WalkService } from 'src/app/core/services/models/walk.service';
import { ActionResponse } from 'src/app/models/action-response.model';
import { RegularUser } from 'src/app/models/users/regular-user.model';
import { User } from 'src/app/models/users/user.model';
import { WalkCard } from 'src/app/models/walks/walk-card.model';
import { WalkInfo } from 'src/app/models/walks/walk-info.model';

@Component({
  selector: 'ktbz-dog-card',
  templateUrl: './dog-card.component.html'
})
export class DogCardComponent implements OnInit {

  @Input()
  walkInfo?: WalkInfo;

  @Output()
  actionEmitter = new EventEmitter<void>();


  response?: ActionResponse;
  isMessageBoxVisible = false;
  userId: string = '';

  constructor(
    private userStore: CurrentUserStoreService,
    private walkService: WalkService,
    private sitterStore: SitterStoreService
    // private tokenStorage: TokenStorageService
    ) { }

  ngOnInit(): void {
  }

  action(walk: WalkInfo) {
    if (walk.isBooked && walk.sitterId === this.userStore.regularUser.id){
      this.disenroll(walk);
    }
    else if (!walk.isBooked) {
      this.enroll(walk);
    }
  }


  enroll(walk: WalkInfo) {
    this.walkService.enroll(walk.id).subscribe(
      (res: any) => {
        this.sitterStore.saveEnrollAction(walk);
        this.actionEmitter.emit();
        this.response = {
          message: res.message,
          isSuccess: true,
        };
        this.isMessageBoxVisible = true;
      },
      (err) => {
        (this.response = {
          message: err.error,
          isSuccess: false,
        }),
          (this.isMessageBoxVisible = true);
      }
    );
  }

  disenroll(walk: WalkInfo) {
    this.walkService.disenroll(walk.id).subscribe(
      (res: any) => {
        this.sitterStore.saveDisenrollAction(walk);
        this.actionEmitter.emit();
        this.response = {
          message: res.message,
          isSuccess: true,
        };
        this.isMessageBoxVisible = true;
      },
      (err) => {
        (this.response = {
          message: err.error,
          isSuccess: false,
        }),
          (this.isMessageBoxVisible = true);
      }
    );
  }

  closeMessageBox(event: boolean) {
    this.isMessageBoxVisible = false;
  }
}
