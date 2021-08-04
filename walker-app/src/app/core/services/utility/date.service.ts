import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class DateService {

  listOfDays(year: number, month: number): number[][] {
    let daysNumber = this.daysOfMonth(year, month + 1);

    let week: number[] = [];
    let x: number[][] = [];
    let day = 1;

    if (this.dayOfWeek(year, month, day) != 1) {
      for (let i = 1; i < this.dayOfWeek(year, month, day); i++) {
        week.push(-1);
      }
    }

    while (day <= daysNumber) {
      week.push(day);
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
