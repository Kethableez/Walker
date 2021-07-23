import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WalkPlannerComponent } from './walk-planner.component';

describe('WalkPlannerComponent', () => {
  let component: WalkPlannerComponent;
  let fixture: ComponentFixture<WalkPlannerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WalkPlannerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WalkPlannerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
