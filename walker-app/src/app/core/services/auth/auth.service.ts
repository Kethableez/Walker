import { SettingService } from './../utility/setting.service';
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

  constructor(private http: HttpClient,
    private setting: SettingService) { }

  registerUser(registerData: any, token: string | null): Observable<any> {
    if (token != null) {
      const url = this.setting.getAuthUrl('registerAdmin', { token: token });

      return this.http.post<any>(url, registerData);
    }
    else {
      const url = this.setting.getAuthUrl('registerUser');

      return this.http.post<any>(url, registerData);
    }
  }

  confirmToken(token: string, code: number) {
    const url = this.setting.getAuthUrl('confirmUser', { token: token });

    return this.http.put<any>(url, code);
  }

  loginUser(credentials: any): Observable<any> {
    const url = this.setting.getAuthUrl('loginUser');

    return this.http.post(url, {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }
}
