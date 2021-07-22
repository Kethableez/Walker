import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SitterButtonsComponent } from './sitter-buttons.component';

describe('SitterButtonsComponent', () => {
  let component: SitterButtonsComponent;
  let fixture: ComponentFixture<SitterButtonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SitterButtonsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SitterButtonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
