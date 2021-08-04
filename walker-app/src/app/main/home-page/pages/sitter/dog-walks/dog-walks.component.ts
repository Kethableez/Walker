import { WalkCard } from './../../../../../models/walks/walk-card.model';
import { WalkService } from './../../../../../core/services/models/walk.service';
import { Component, OnInit } from '@angular/core';
import { ActionResponse } from 'src/app/models/action-response.model';
import { SitterService } from 'src/app/core/services/models/sitter.service';

@Component({
  selector: 'ktbz-dog-walks',
  templateUrl: './dog-walks.component.html',
})
export class DogWalksComponent implements OnInit {
  constructor(
    private walkService: WalkService,
    private sitterService: SitterService
  ) { }
  allWalks: WalkCard[] = [];
  sitterWalks: WalkCard[] = [];

  allView = true;
  currentView = 'Wszystkie';

  response?: ActionResponse;
  isMessageBoxVisible = false;

  ngOnInit(): void {
    this.walkService.getAll().subscribe((res) => (this.allWalks = res));
    this.sitterService.getWalks().subscribe((res) => (this.sitterWalks = res));
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

  changeView() {
    this.allView ? (this.allView = false, this.currentView = 'Nadchodzące') : (this.allView = true, this.currentView = 'Wzystkie');
  }
}
