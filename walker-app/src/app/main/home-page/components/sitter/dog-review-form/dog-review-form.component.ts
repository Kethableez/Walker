import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'ktbz-dog-review-form',
  templateUrl: './dog-review-form.component.html'
})
export class DogReviewFormComponent implements OnInit {

  @Input()
  walkId: string = '';

  constructor(private builder: FormBuilder) { }

  dogReviewForm = this.builder.group({
    dogPhoto: ['', Validators.required],
    reivewBody: ['', Validators.required],
    walkId: ['', Validators.required],
  })

  ngOnInit(): void {
    this.dogReviewForm.patchValue({
      walkId: this.walkId
    })
  }


  submitReview() {

  }
}
