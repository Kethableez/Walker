import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private serviceUrl = environment.apiBaseUrl + '/image';

  constructor(private http: HttpClient) { }

  getBackground() {
    return this.http.get<any>(this.serviceUrl + '/background');
  }

  uploadDogPhoto(dogPhoto: FormData, dogId: string) {
    return this.http.post<any>(this.serviceUrl + '/dog/upload/' + dogId, dogPhoto);
  }

  uploadUserPhoto(userPhoto: FormData, userId: string) {
    return this.http.post<any>(this.serviceUrl + '/user/upload/' + userId, userPhoto);
  }
}
