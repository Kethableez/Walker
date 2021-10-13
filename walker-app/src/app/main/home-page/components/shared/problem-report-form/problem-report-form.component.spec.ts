import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemReportFormComponent } from './problem-report-form.component';

describe('ProblemReportFormComponent', () => {
  let component: ProblemReportFormComponent;
  let fixture: ComponentFixture<ProblemReportFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProblemReportFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProblemReportFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
