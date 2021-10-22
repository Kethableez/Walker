import { SitterData } from './../../../models/users/sitter.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DogCard } from 'src/app/models/dogs/dog-card.model';
import { WalkInfo } from 'src/app/models/walks/walk-info.model';
import { PastWalkInfo } from './../../../models/walks/past-walk-info.model';
import { SettingService } from './../utility/setting.service';
import { WalkCard } from 'src/app/models/walks/walk-card.model';

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

  getWalks(): Observable<WalkCard[]> {
    const url = this.setting.getSitterUrl('getWalks');

    return this.http.get<WalkCard[]>(url);
  }

  getHistory(): Observable<WalkCard[]> {
    const url = this.setting.getSitterUrl('getHistory');

    return this.http.get<WalkCard[]>(url);
  }

  getSitterReviews(sitterId: string) {
    const url = this.setting.getReviewUrl('getSitterReviews', {sitterId: sitterId});

    return this.http.get(url);
  }

  getSitterData(username?: string) {
    const url = this.setting.getSitterUrl('getData', { username: username });

    return this.http.get<SitterData>(url);
  }

  getSitterDataByUsername(username: string) {
    const url = this.setting.getSitterUrl('getDataByUsername', { username: username });

    return this.http.get<SitterData>(url);
  }
}
