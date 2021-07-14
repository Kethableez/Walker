import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private serviceUrl = environment.apiBaseUrl + '/utility_images';

  constructor(private http: HttpClient) { }

  getBackground() {
    return this.http.get<any>(this.serviceUrl + '/background');
  }
}
