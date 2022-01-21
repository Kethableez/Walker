import { SitterStoreService } from './../../../../../core/services/store/sitter-store.service';
import { WalkCard } from './../../../../../models/walks/walk-card.model';
import { WalkService } from './../../../../../core/services/models/walk.service';
import { Component, OnInit, OnChanges, SimpleChanges, ChangeDetectorRef } from '@angular/core';
import { ActionResponse } from 'src/app/models/action-response.model';
import { SitterService } from 'src/app/core/services/models/sitter.service';
import { WalkInfo } from 'src/app/models/walks/walk-info.model';
import { WalkWithFilters } from 'src/app/models/walks/walk-with-filters.model';
import { CurrentUserStoreService } from 'src/app/core/services/store/current-user-store.service';
import { filterByKey } from 'src/app/core/services/utility/utility.model';
import { Walk } from 'src/app/models/walks/walk.model';

@Component({
  selector: 'ktbz-dog-walks',
  templateUrl: './dog-walks.component.html',
})
export class DogWalksComponent implements OnInit {
  constructor(
    private walkService: WalkService,
    private sitterStore: SitterStoreService,
    private changes: ChangeDetectorRef
  ) { }
  allWalks: WalkCard[] = [];
  walksWithFilters?: WalkWithFilters;
  sitterWalks: WalkCard[] = this.sitterStore.incomingWalks;

  allView = true;
  currentView = 'Wszystkie';

  response?: ActionResponse;
  isMessageBoxVisible = false;

  ngOnInit(): void {
    // this.walkService.getWalksWithFilters().subscribe((res: WalkWithFilters) => {
    //   console.log(res);
    //   // this.allWalks = res.walks;
    // } );

    this.walkService.getAll().subscribe((res: WalkCard[]) => {
      console.log(res);
      this.allWalks = res;
    })
  }


  closeMessageBox(event: boolean) {
    this.isMessageBoxVisible = false;
  }

  changeView() {
    this.allView ? (this.allView = false, this.currentView = 'Nadchodzące') : (this.allView = true, this.currentView = 'Wzystkie');
  }

  getActionEvent() {
    this.sitterWalks = this.sitterStore.incomingWalks;
  }
}
