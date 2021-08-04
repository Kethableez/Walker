import { WalkCard } from './../../../models/walks/walk-card.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DogCard } from 'src/app/models/dogs/dog-card.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SitterService {

  private serviceUrl = environment.apiBaseUrl + '/sitter';

  constructor(private http: HttpClient) { }

  getDogs(): Observable<DogCard[]> {
    return this.http.get<DogCard[]>(this.serviceUrl + '/dogs');
  }

  getWalks(): Observable<WalkCard[]> {
    return this.http.get<WalkCard[]>(this.serviceUrl + '/walks');
  }

  getHistory(): Observable<WalkCard[]> {
    return this.http.get<WalkCard[]>(this.serviceUrl + '/history');
  }
}
