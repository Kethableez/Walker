import { PastWalkInfo } from './../../../models/walks/past-walk-info.model';
import { SettingService } from './../utility/setting.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DogCard } from 'src/app/models/dogs/dog-card.model';
import { PastWalkCard } from 'src/app/models/walks/past-walk-card.model';
import { OwnerData } from 'src/app/models/users/owner.model';
import { WalkCard } from 'src/app/models/walks/walk-card.model';

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  constructor(private http: HttpClient,
    private setting: SettingService) { }

  getDogs(): Observable<DogCard[]> {
    const url = this.setting.getOwnerUrl('getDogs');

    return this.http.get<DogCard[]>(url);
  }

  getWalks(): Observable<WalkCard[]> {
    const url = this.setting.getOwnerUrl('getWalks');

    return this.http.get<WalkCard[]>(url);
  }

  getHistory(): Observable<WalkCard[]> {
    const url = this.setting.getOwnerUrl('getHistory');

    return this.http.get<WalkCard[]>(url);
  }

  getOwnerData(username?: string) {
    const url = this.setting.getOwnerUrl('getData', {username: username});

    return this.http.get<OwnerData>(url);
  }

  getOwnerDataByUsername(username: string) {
    const url = this.setting.getOwnerUrl('getDataByUsername', { username: username });

    return this.http.get<OwnerData>(url);
  }
}
