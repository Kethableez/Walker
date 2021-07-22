import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private serviceUrl = environment.apiBaseUrl + '/auth';

  constructor(private http: HttpClient) { }

  registerUser(registerData: any, token: string | null) {
    if (token != null) return this.http.post<any>(this.serviceUrl + '/register/' + token, registerData);
    else return this.http.post<any>(this.serviceUrl + '/register/', registerData);
  }

  confirmToken(token: string, code: number) {
    return this.http.put<any>(this.serviceUrl + '/confirm/' + token, code);
  }

  loginUser(credentials: any): Observable<any> {
    return this.http.post(this.serviceUrl + '/login', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }
}
