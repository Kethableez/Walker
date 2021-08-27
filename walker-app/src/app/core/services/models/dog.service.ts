import { SettingService } from './../utility/setting.service';
import { DogCard } from './../../../models/dogs/dog-card.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DogService {

  constructor(private http: HttpClient,
    private setting: SettingService) { }

  createDog(dogRequest: any) {
    const url = this.setting.getDogUrl('createDog');

    return this.http.post<any>(url, dogRequest);
  }

  getDog(dogId: string): Observable<DogCard> {
    const url = this.setting.getDogUrl('getDog', { dogId: dogId });

    return this.http.get<DogCard>(url);
  }
}
