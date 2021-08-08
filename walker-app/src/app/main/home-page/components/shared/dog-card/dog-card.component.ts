import { Component, Input, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/core/services/auth/token-storage.service';
import { UserService } from 'src/app/core/services/models/user.service';
import { WalkService } from 'src/app/core/services/models/walk.service';
import { ActionResponse } from 'src/app/models/action-response.model';
import { RegularUser } from 'src/app/models/users/regular-user.model';
import { User } from 'src/app/models/users/user.model';
import { WalkCard } from 'src/app/models/walks/walk-card.model';

@Component({
  selector: 'ktbz-dog-card',
  templateUrl: './dog-card.component.html'
})
export class DogCardComponent implements OnInit {

  @Input()
  walkCard?: WalkCard;

  response?: ActionResponse;
  isMessageBoxVisible = false;
  userId: string = '';

  constructor(private walkService: WalkService,
    private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
  }

  action(card: WalkCard) {
    if (card.walk.booked && card.walk.sitterId === this.tokenStorage.getUser().id){
      this.disenroll(card.walk.id);
    }
    else if (!card.walk.booked) {
      this.enroll(card.walk.id);
    }
  }


  enroll(id: string) {
    this.walkService.enroll(id).subscribe(
      (res: any) => {
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

  disenroll(id: string) {
    this.walkService.disenroll(id).subscribe(
      (res: any) => {
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
