import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SitterReviewFormComponent } from './sitter-review-form.component';

describe('SitterReviewFormComponent', () => {
  let component: SitterReviewFormComponent;
  let fixture: ComponentFixture<SitterReviewFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SitterReviewFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SitterReviewFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
