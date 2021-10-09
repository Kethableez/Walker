import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActionResponse } from 'src/app/models/action-response.model';
import { Role } from 'src/app/models/enums/role.model';
import { RegularUser } from 'src/app/models/users/regular-user.model';
import { User } from 'src/app/models/users/user.model';
import { SettingService } from './../utility/setting.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient,
    private setting: SettingService) { }

  getUserData(username?: string): Observable<User> {
    const url = this.setting.getUserUrl('getUserData', {username: username});
    console.log(url);

    return this.http.get<User>(url);
  }

  getUserRole(username?: string): Observable<Role> {
    const url = this.setting.getUserUrl('getRole', {username: username});

    return this.http.get<Role>(url);
  }

  getUserDataParam(username: string): Observable<User> {
    const url = this.setting.getUserUrl('getUserData', { username: username });

    return this.http.get<User>(url);
  }

  chagneData(data: any): Observable<ActionResponse> {
    const url = this.setting.getUserUrl('changeData');

    return this.http.put<any>(url, data);
  }

  changePassword(data: any): Observable<ActionResponse> {
    const url = this.setting.getUserUrl('changePassword');

    return this.http.put<any>(url, data);
  }

  changeDescription(data: any): Observable<ActionResponse> {
    const url = this.setting.getUserUrl('changeDescription');

    return this.http.put<any>(url, data);
  }

  changeAvatar(data: FormData): Observable<ActionResponse> {
    const url = this.setting.getUserUrl('changeAvatar');

    return this.http.put<any>(url, data);
  }
}
