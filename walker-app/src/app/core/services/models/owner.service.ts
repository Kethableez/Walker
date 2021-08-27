import { SettingService } from './../utility/setting.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DogCard } from 'src/app/models/dogs/dog-card.model';
import { PastWalkCard } from 'src/app/models/walks/past-walk-card.model';

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

  getWalks(): Observable<any> {
    const url = this.setting.getOwnerUrl('getWalks');

    return this.http.get<any>(url);
  }

  getSitters(): Observable<any> {
    const url = this.setting.getOwnerUrl('getSitters');

    return this.http.get<any>(url);
  }

  getHistory(): Observable<PastWalkCard[]> {
    const url = this.setting.getOwnerUrl('getHistory');

    return this.http.get<PastWalkCard[]>(url);
  }
}
