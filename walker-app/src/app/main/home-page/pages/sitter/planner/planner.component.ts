import { SitterStoreService } from './../../../../../core/services/store/sitter-store.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SitterService } from 'src/app/core/services/models/sitter.service';
import { WalkCard } from 'src/app/models/walks/walk-card.model';
import { WalkInfo } from 'src/app/models/walks/walk-info.model';
import { DateService } from './../../../../../core/services/utility/date.service';

export interface WalkCardMapper {
  day: number;
  walkInfo: WalkInfo[];
}

@Component({
  selector: 'ktbz-planner',
  templateUrl: './planner.component.html',
})
export class PlannerComponent implements OnInit {
  currentDay: Date = new Date(Date.now());
  currentWeek: number = -1;
  calendar: WalkCardMapper[][] = [];
  fullView = false;

  constructor(
    private dateService: DateService,
    private sitterStore: SitterStoreService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    console.log(this.currentDay)
    this.calendar = this.dateService.listOfDays(
      this.currentDay.getFullYear(),
      this.currentDay.getMonth()
    );
    this.currentWeek = this.findWeek();
    this.mapWithDay(this.sitterStore.incomingWalks, this.calendar);
  }

  mapWithDay(walk: WalkInfo[], calendar: WalkCardMapper[][]): void {
    walk
      .filter(
        (walk) =>
          new Date(walk.walkDateTime).getMonth() == this.currentDay.getMonth()
      )
      .forEach(dog => {
        let walkDate = new Date(dog.walkDateTime);
        console.log(dog.walkDateTime)
        console.log(walkDate.getDate())
        calendar.forEach((week) => {
          week.find((day) => day.day == walkDate.getDate())?.walkInfo.push(dog);
        });
      });
  }

  findWeek() {
    return this.calendar.findIndex((week) => week.find((m) => m.day == this.currentDay.getDate()));
  }

  changeView() {
    this.fullView ? (this.fullView = false) : (this.fullView = true);
  }

  dogProfile(dogId: string) {
    this.router.navigate(['dog/' + dogId], {relativeTo: this.route});
  }
}
