import { Router, ActivatedRoute } from '@angular/router';
import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { SitterService } from 'src/app/core/services/models/sitter.service';
import { WalkCard } from 'src/app/models/walks/walk-card.model';
import { DateService } from './../../../../../core/services/utility/date.service';

export interface WalkCardMapper {
  day: number;
  walkCard: WalkCard[];
}

@Component({
  selector: 'ktbz-planner',
  templateUrl: './planner.component.html',
})
export class PlannerComponent implements OnInit {
  currentDay: Date = new Date(Date.now());
  currentWeek: number = -1;
  calendar: WalkCardMapper[][] = [];
  fullView = true;

  constructor(
    private dateService: DateService,
    private sitterService: SitterService,
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
    this.sitterService.getWalks().subscribe((res) => {
      this.mapWithDay(res, this.calendar);
    });
  }

  mapWithDay(walkCards: WalkCard[], calendar: WalkCardMapper[][]): void {
    walkCards
      .filter(
        (walkCard) =>
          new Date(walkCard.walk.walkDateTime).getMonth() == this.currentDay.getMonth()
      )
      .forEach(dog => {
        let walkDate = new Date(dog.walk.walkDateTime);
        console.log(dog.walk.walkDateTime)
        console.log(walkDate.getDate())
        calendar.forEach((week) => {
          week.find((day) => day.day == walkDate.getDate())?.walkCard.push(dog);
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
