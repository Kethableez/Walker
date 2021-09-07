import { SettingService } from './../utility/setting.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http: HttpClient,
    private setting: SettingService) { }

  uploadDogPhoto(dogPhoto: FormData, dogId: string) {
    const url = this.setting.getImageUrl('uploadDogImage', { dogId: dogId });

    return this.http.post<any>(url, dogPhoto);
  }

  uploadUserPhoto(userPhoto: FormData, userId: string) {
    const url = this.setting.getImageUrl('uploadUserImage', { userId: userId });

    return this.http.post<any>(url, userPhoto);
  }

  uploadDogReviewImage(dogPhoto: FormData, reviewId: string) {
    const url = this.setting.getImageUrl('uploadDogReviewImage', { reviewId: reviewId });

    return this.http.post<any>(url, dogPhoto);
  }
}
