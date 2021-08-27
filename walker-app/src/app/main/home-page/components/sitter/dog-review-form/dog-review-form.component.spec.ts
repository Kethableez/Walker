import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DogReviewFormComponent } from './dog-review-form.component';

describe('DogReviewFormComponent', () => {
  let component: DogReviewFormComponent;
  let fixture: ComponentFixture<DogReviewFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DogReviewFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DogReviewFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
