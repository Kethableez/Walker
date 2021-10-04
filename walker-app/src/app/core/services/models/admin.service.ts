import { Observable } from 'rxjs';
import { SettingService } from './../utility/setting.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { UserInfo } from 'src/app/models/users/user-info.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient,
              private settings: SettingService) { }

  // TODO: Dorobić modele!
  get usersList(): Observable<UserInfo[]> {
    const url = this.settings.getAdminUrl('getUsers');

    return this.http.get<UserInfo[]>(url);
  }

  get dogList(): Observable<any[]> {
    const url = this.settings.getAdminUrl('getDogs');

    return this.http.get<any[]>(url);
  }

  get walkList(): Observable<any[]> {
    const url = this.settings.getAdminUrl('getWalks');

    return this.http.get<any[]>(url);
  }
}
