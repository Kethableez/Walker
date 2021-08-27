import { SettingService } from './../utility/setting.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegularUser } from 'src/app/models/users/regular-user.model';
import { User } from 'src/app/models/users/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient,
    private setting: SettingService) { }

  getUserData(): Observable<User | RegularUser> {
    const url = this.setting.getUserUrl('getLoggedUserData');

    return this.http.get<User | RegularUser>(url);
  }

  getUserDataParam(username: string): Observable<any> {
    const url = this.setting.getUserUrl('getUserData', { username: username });

    return this.http.get<any>(url);
  }
}
