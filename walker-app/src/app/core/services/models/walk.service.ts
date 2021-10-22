import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { WalkCard } from 'src/app/models/walks/walk-card.model';
import { SettingService } from '../utility/setting.service';
import { WalkInfo } from 'src/app/models/walks/walk-info.model';
import { WalkWithFilters } from 'src/app/models/walks/walk-with-filters.model';

@Injectable({
  providedIn: 'root',
})
export class WalkService {

  constructor(private http: HttpClient,
    private setting: SettingService) {}

  getAll(): Observable<WalkCard[]> {
    const url = this.setting.getWalkUrl('getAllWalks');

    return this.http.get<WalkCard[]>(url);
  }

  getWalksWithFilters(): Observable<WalkWithFilters> {
    return this.http.get<WalkWithFilters>('http://localhost:8080/walk/allWithFilters');
  }

  getWalk(walkId: string): Observable<WalkCard> {
    const url = this.setting.getWalkUrl('getWalkCard', { id: walkId });

    return this.http.get<WalkCard>(url);
  }

  createWalk(walkRequest: any) {
    const url = this.setting.getWalkUrl('createWalk');

    return this.http.post<any>(url, walkRequest);
  }

  enroll(walkId: string) {
    const url = this.setting.getWalkUrl('walkEnroll', { id: walkId });

    return this.http.post(url, {});
  }

  disenroll(walkId: string) {
    const url = this.setting.getWalkUrl('walkDisenroll', { id: walkId });

    return this.http.post(url, {});
  }
}
