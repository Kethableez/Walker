import { NotificationService } from 'src/app/core/services/utility/notification.service';
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
  templateUrl: './dog-card.component.html',
})
export class DogCardComponent implements OnInit {
  @Input()
  walkCard?: WalkCard;

  @Output()
  actionEmitter = new EventEmitter<void>();

  userId: string = '';

  constructor(
    private userStore: CurrentUserStoreService,
    private walkService: WalkService,
    private sitterStore: SitterStoreService,
    private notification: NotificationService
  ) {}

  ngOnInit(): void {}

  action(walkCard: WalkCard) {
    if (
      walkCard.walk.booked &&
      walkCard.walk.sitterId === this.userStore.regularUser.id
    ) {
      this.disenroll(walkCard);
    } else if (!walkCard.walk.booked) {
      this.enroll(walkCard);
    }
  }

  enroll(walkCard: WalkCard) {
    this.walkService.enroll(walkCard.walk.id).subscribe(
      (response) => {
        this.notification.dispatchNotification(true, response.message, false);
        this.sitterStore.saveEnrollAction(walkCard);
        this.actionEmitter.emit();
      },
      (error) => this.notification.dispatchNotification(true, error.error, true)
    );
  }

  disenroll(walkCard: WalkCard) {
    this.walkService.disenroll(walkCard.walk.id).subscribe(
      (response) => {
        this.notification.dispatchNotification(true, response.message, false);
        this.sitterStore.saveDisenrollAction(walkCard);
        this.actionEmitter.emit();
      },
      (error) => this.notification.dispatchNotification(true, error.error, true)
    );
  }
}
