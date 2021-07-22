import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerButtonsComponent } from './owner-buttons.component';

describe('OwnerButtonsComponent', () => {
  let component: OwnerButtonsComponent;
  let fixture: ComponentFixture<OwnerButtonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnerButtonsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnerButtonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
