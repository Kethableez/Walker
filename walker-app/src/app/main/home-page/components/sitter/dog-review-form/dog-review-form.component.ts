import { ImageService } from 'src/app/core/services/models/image.service';
import { ActionResponse } from 'src/app/models/action-response.model';
import { ReviewService } from './../../../../../core/services/models/review.service';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'ktbz-dog-review-form',
  templateUrl: './dog-review-form.component.html'
})
export class DogReviewFormComponent implements OnInit {

  @Input()
  walkId: string = '';

  selectedFile: string = ' ';

  constructor(private builder: FormBuilder, private reviewService: ReviewService, private imageService: ImageService) { }

  dogReviewForm = this.builder.group({
    dogPhoto: ['', Validators.required],
    reviewBody: ['', Validators.required],
    walkId: ['', Validators.required],
  })

  ngOnInit(): void {
    this.dogReviewForm.patchValue({
      walkId: this.walkId
    })
  }


  submitReview() {
    this.reviewService.addDogReview(this.dogReviewForm.value).subscribe(
      (success: ActionResponse) => {
        let reviewId = success.message;
        let dogPhoto = new FormData();
        dogPhoto.append('imageFile', this.selectedFile);

        this.imageService.uploadDogReviewImage(dogPhoto, reviewId).subscribe(
        )
      },
    )
  }

  onSelectFile(e: any) {
    if (e.target.files.length > 0) {
      const f = e.target.files[0];
      this.selectedFile = e.target.files[0];
      this.dogReviewForm.patchValue({
        dogPhoto: e.target.files[0].name,
      });
    }
  }
}
