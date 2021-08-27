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

  constructor(private builder: FormBuilder) { }

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

  }
}
