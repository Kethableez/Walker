import { DogInfo } from './../../../models/dogs/dog-info.model';
import { PastWalkInfo } from './../../../models/walks/past-walk-info.model';
import { SettingService } from './../utility/setting.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DogCard } from 'src/app/models/dogs/dog-card.model';
import { PastWalkCard } from 'src/app/models/walks/past-walk-card.model';
import { OwnerData } from 'src/app/models/users/owner.model';

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  constructor(private http: HttpClient,
    private setting: SettingService) { }

  getDogs(): Observable<DogInfo[]> {
    const url = this.setting.getOwnerUrl('getDogs');

    return this.http.get<DogInfo[]>(url);
  }

  getWalks(): Observable<any> {
    const url = this.setting.getOwnerUrl('getWalks');

    return this.http.get<any>(url);
  }

  getSitters(): Observable<any> {
    const url = this.setting.getOwnerUrl('getSitters');

    return this.http.get<any>(url);
  }

  getHistory(): Observable<PastWalkInfo[]> {
    const url = this.setting.getOwnerUrl('getHistory');

    return this.http.get<PastWalkInfo[]>(url);
  }

  getOwnerData(username?: string) {
    const url = this.setting.getOwnerUrl('getData', {username: username});

    return this.http.get<OwnerData>(url);
  }

  getOwnerDataByUsername(username: string) {
    console.log(username);
    const url = this.setting.getOwnerUrl('getDataByUsername', { username: username });

    return this.http.get<OwnerData>(url);
  }
}
