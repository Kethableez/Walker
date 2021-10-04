import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { interval, Observable } from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  isInit = true;

  constructor(private http: HttpClient) { }

  private get NotificationUrl(): Observable<any> {
    return this.http.get<any>('http://localhost:8080/notification/get_notification');
  }
}
