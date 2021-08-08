import { DogCard } from './../../../models/dogs/dog-card.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DogService {

  private serviceUrl = environment.apiBaseUrl + '/dog/';

  constructor(private http: HttpClient) { }

  createDog(dogRequest: any) {
    return this.http.post<any>(this.serviceUrl + 'create', dogRequest);
  }

  getDog(dogId: string): Observable<DogCard> {
    return this.http.get<DogCard>(this.serviceUrl + dogId);
  }
}
