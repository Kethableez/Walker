import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerDogsComponent } from './owner-dogs.component';

describe('OwnerDogsComponent', () => {
  let component: OwnerDogsComponent;
  let fixture: ComponentFixture<OwnerDogsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnerDogsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnerDogsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
