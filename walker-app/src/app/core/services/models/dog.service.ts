import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DogService {

  private serviceUrl = environment.apiBaseUrl + '/dog';

  constructor(private http: HttpClient) { }

  createDog(dogRequest: any) {
    return this.http.post<any>(this.serviceUrl + '/create', dogRequest);
  }
}
