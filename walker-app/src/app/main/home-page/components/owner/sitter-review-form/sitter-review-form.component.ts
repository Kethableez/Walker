import { ActionResponse } from 'src/app/models/action-response.model';
import { ReviewService } from './../../../../../core/services/models/review.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ktbz-sitter-review-form',
  templateUrl: './sitter-review-form.component.html'
})
export class SitterReviewFormComponent implements OnInit {

  @Input()
  walkId: string = '';

  rate: number = 3;

  constructor(private builder: FormBuilder,
    private reviewService: ReviewService) { }

  sitterReviewForm = this.builder.group({
    rating: ['', Validators.required],
    reivewBody: ['', Validators.required],
    walkId: ['', Validators.required],
  })

  ngOnInit(): void {
    this.sitterReviewForm.patchValue({
      walkId: this.walkId
    })
  }


  submitReview() {
    this.reviewService.addSitterReview(this.sitterReviewForm.value).subscribe(
    )
  }
}
