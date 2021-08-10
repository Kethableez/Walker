import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DogCard } from 'src/app/models/dogs/dog-card.model';
import { PastWalkCard } from 'src/app/models/walks/past-walk-card.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  private serviceUrl = environment.apiBaseUrl + '/owner';

  constructor(private http: HttpClient) { }

  getDogs(): Observable<DogCard[]> {
    return this.http.get<DogCard[]>(this.serviceUrl + '/dogs');
  }

  getWalks(): Observable<any> {
    return this.http.get<any>(this.serviceUrl + '/walks');
  }

  getSitters(): Observable<any> {
    return this.http.get<any>(this.serviceUrl + '/sitters');
  }

  getHistory(): Observable<PastWalkCard[]> {
    return this.http.get<PastWalkCard[]>(this.serviceUrl + '/history');
  }
}
