import { ActionResponse } from 'src/app/models/action-response.model';
import { Observable } from 'rxjs';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { SettingService } from "../utility/setting.service";

@Injectable({
  providedIn: 'root',
})
export class ReviewService {

  constructor(private http: HttpClient,
    private setting: SettingService) { }

    addDogReview(data: any): Observable<ActionResponse> {
      const url = this.setting.getReviewUrl('addDogReview');

      return this.http.post<ActionResponse>(url, data);
    }

    addSitterReview(data: any): Observable<ActionResponse> {
      const url = this.setting.getReviewUrl('addSitterReview');

      return this.http.post<ActionResponse>(url, data);
    }

}
