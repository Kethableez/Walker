import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegularUser } from 'src/app/models/users/regular-user.model';
import { User } from 'src/app/models/users/user.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private serviceUrl = environment.apiBaseUrl + '/user';

  constructor(private http: HttpClient) { }

  getUserData(): Observable<User | RegularUser> {
    return this.http.get<User | RegularUser>(this.serviceUrl + '/get_data');
  }

  getUserDataParam(username: string): Observable<User | RegularUser> {
    return this.http.get<User | RegularUser>(this.serviceUrl + '/get_data/' + username);
  }
}
