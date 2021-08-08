import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { WalkCard } from 'src/app/models/walks/walk-card.model';

@Injectable({
  providedIn: 'root',
})
export class WalkService {
  private serviceUrl = environment.apiBaseUrl + '/walk/';

  constructor(private http: HttpClient) {}

  getAll(): Observable<WalkCard[]> {
    return this.http.get<WalkCard[]>(this.serviceUrl + 'all');
  }

  getWalk(walkId: string): Observable<WalkCard> {
    return this.http.get<WalkCard>(this.serviceUrl + walkId);
  }

  createWalk(walkRequest: any) {
    return this.http.post<any>(this.serviceUrl + 'create', walkRequest);
  }

  enroll(id: string) {
    return this.http.post(this.serviceUrl + 'enroll/' + id, {});
  }

  disenroll(id: string) {
    return this.http.post(this.serviceUrl + 'disenroll/' + id, {});
  }
}
