import { ActionResponse } from 'src/app/models/action-response.model';
import { UserWithInfo } from './../../../models/users/user-with-info.model';
import { Observable } from 'rxjs';
import { SettingService } from './../utility/setting.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { WalkAdminInfo } from 'src/app/models/walks/walk-admin-info.model';
import { DogCard } from 'src/app/models/dogs/dog-card.model';
import { WalkCard } from 'src/app/models/walks/walk-card.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient,
              private settings: SettingService) { }

  // TODO: Dorobić modele!
  get usersList(): Observable<UserWithInfo[]> {
    const url = this.settings.getAdminUrl('getUsers');

    return this.http.get<UserWithInfo[]>(url);
  }

  get dogList(): Observable<DogCard[]> {
    const url = this.settings.getAdminUrl('getDogs');

    return this.http.get<DogCard[]>(url);
  }

  get walkList(): Observable<WalkCard[]> {
    const url = this.settings.getAdminUrl('getWalks');

    return this.http.get<WalkCard[]>(url);
  }

  banUser(userId: string): Observable<ActionResponse> {
    const url = this.settings.getAdminUrl('banUser', {userId: userId});

    return this.http.post<ActionResponse>(url, {});
  }

  unbanUser(userId: string): Observable<ActionResponse> {
    const url = this.settings.getAdminUrl('unbanUser', {userId: userId});

    return this.http.post<ActionResponse>(url, {});
  }

  blockUser(userId: string): Observable<ActionResponse> {
    const url = this.settings.getAdminUrl('blockUser', {userId: userId});

    return this.http.post<ActionResponse>(url, {});
  }

  unblockUser(userId: string): Observable<ActionResponse> {
    const url = this.settings.getAdminUrl('unblockUser', {userId: userId});

    return this.http.post<ActionResponse>(url, {});
  }

  removeUser(userId: string): Observable<ActionResponse> {
    const url = this.settings.getAdminUrl('removeUser', {userId: userId});

    return this.http.delete<ActionResponse>(url);
  }
}
