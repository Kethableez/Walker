import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/user.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private serviceUrl = environment.apiBaseUrl + '/user';

  constructor(private http: HttpClient) { }

  getUserData(): Observable<User> {
    return this.http.get<User>(this.serviceUrl + '/getData');
  }
}
