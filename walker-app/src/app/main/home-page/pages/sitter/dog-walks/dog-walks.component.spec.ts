import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DogWalksComponent } from './dog-walks.component';

describe('DogWalksComponent', () => {
  let component: DogWalksComponent;
  let fixture: ComponentFixture<DogWalksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DogWalksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DogWalksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
