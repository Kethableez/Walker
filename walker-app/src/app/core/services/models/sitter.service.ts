import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DogCard } from 'src/app/models/dogs/dog-card.model';
import { WalkInfo } from 'src/app/models/walks/walk-info.model';
import { PastWalkInfo } from './../../../models/walks/past-walk-info.model';
import { SettingService } from './../utility/setting.service';

@Injectable({
  providedIn: 'root'
})
export class SitterService {

  constructor(private http: HttpClient,
    private setting: SettingService) { }

  getDogs(): Observable<DogCard[]> {
    const url = this.setting.getSitterUrl('getDogs');

    return this.http.get<DogCard[]>(url);
  }

  getWalks(): Observable<WalkInfo[]> {
    const url = this.setting.getSitterUrl('getWalks');

    return this.http.get<WalkInfo[]>(url);
  }

  getHistory(): Observable<PastWalkInfo[]> {
    const url = this.setting.getSitterUrl('getHistory');

    return this.http.get<PastWalkInfo[]>(url);
  }
}
