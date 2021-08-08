import { WalkCardMapper } from './../../../main/home-page/pages/sitter/planner/planner.component';
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class DateService {

  listOfDays(year: number, month: number): WalkCardMapper[][] {
    let daysNumber = this.daysOfMonth(year, month + 1);

    let week: WalkCardMapper[] = [];
    let x: WalkCardMapper[][] = [];
    let day = 1;

    if (this.dayOfWeek(year, month, day) != 1) {
      for (let i = 1; i < this.dayOfWeek(year, month, day); i++) {
        week.push({
          'day': -1,
          'walkCard': []
        });
      }
    }

    while (day <= daysNumber) {
      week.push({
        'day': day,
        'walkCard': []
      });
      if (this.dayOfWeek(year, month, day) == 7) {
        x.push(week);
        week = [];
      }
      day++;
    }
    if (week.length != 0) {
      x.push(week);
    }


    return x;
  }

  daysOfMonth(year: number, month: number): number {
    return new Date(year, month, 0).getDate();
  }

  dayOfWeek(year: number, month: number, day: number): number {
    let dayOfWeek = new Date(year, month, day).getDay();
    if (dayOfWeek != 0) return dayOfWeek;
    else return 7;
  }
}
