import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActionResponse } from 'src/app/models/action-response.model';
import { WalkCard } from 'src/app/models/walks/walk-card.model';
import { WalkWithFilters } from 'src/app/models/walks/walk-with-filters.model';
import { CurrentUserStoreService } from '../store/current-user-store.service';
import { SettingService } from '../utility/setting.service';

@Injectable({
  providedIn: 'root',
})
export class WalkService {

  constructor(private http: HttpClient,
    private setting: SettingService,
    private userStore: CurrentUserStoreService) {}

  getAll(): Observable<WalkCard[]> {
    const url = this.setting.getWalkUrl('getAllWalks');

    return this.http.get<WalkCard[]>(url);
  }

  getWalksWithFilters(): Observable<WalkWithFilters> {
    const headers = new HttpHeaders().set('districtCode', this.userStore.districtCode);

    return this.http.get<WalkWithFilters>('http://localhost:8080/walk/allWithFilters', { headers: headers });
  }

  getWalk(walkId: string): Observable<WalkCard> {
    const url = this.setting.getWalkUrl('getWalkCard', { id: walkId });

    return this.http.get<WalkCard>(url);
  }

  createWalk(walkRequest: any) {
    const url = this.setting.getWalkUrl('createWalk');

    return this.http.post<any>(url, walkRequest);
  }

  enroll(walkId: string):Observable<ActionResponse> {
    const url = this.setting.getWalkUrl('walkEnroll', { id: walkId });

    return this.http.post<ActionResponse>(url, {});
  }

  disenroll(walkId: string): Observable<ActionResponse> {
    const url = this.setting.getWalkUrl('walkDisenroll', { id: walkId });

    return this.http.post<ActionResponse>(url, {});
  }
}
