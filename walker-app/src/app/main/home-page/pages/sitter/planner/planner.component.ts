import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { SitterService } from 'src/app/core/services/models/sitter.service';
import { WalkCard } from 'src/app/models/walks/walk-card.model';
import { DateService } from './../../../../../core/services/utility/date.service';

export interface WalkDate {
  WalkCard: WalkCard;
  day: Number;
}

@Component({
  selector: 'ktbz-planner',
  templateUrl: './planner.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PlannerComponent implements OnInit {

  currentDay: Date = new Date(Date.now());
  currentWeek: number = -1;
  calendar: number[][] = [];
  dogs: WalkCard[] = [];
  walkDate: WalkDate[] = [];
  fullView = true;

  constructor(private dateService: DateService,
    private sitterService: SitterService) { }

  ngOnInit(): void {
    this.calendar = this.dateService.listOfDays(this.currentDay.getFullYear(), this.currentDay.getMonth());
    this.currentWeek = this.findWeek();
    this.sitterService.getWalks().subscribe(res =>  {
      this.dogs = res;

      this.dogs.forEach(dog => {
        let walk: WalkDate = {
          WalkCard: dog,
          day: new Date(dog.walk.walkDateTime).getDate()
        }
        this.walkDate.push(walk);
      })


      console.log(this.walkDate)
    });
  }


  findWeek() {
    return this.calendar.findIndex(week => week.includes(this.currentDay.getDate()));
  }

  changeView() {
    this.fullView ? this.fullView = false : this.fullView = true;
  }

  doSomething(day: number) : string  {
    console.log('call')
    let index = this.walkDate.findIndex(walk => walk.day == day);
    if (index != -1) return this.walkDate[index].WalkCard.dog.name;
    else return '';
  }
}
