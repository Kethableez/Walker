import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DogCreatorComponent } from './dog-creator.component';

describe('DogCreatorComponent', () => {
  let component: DogCreatorComponent;
  let fixture: ComponentFixture<DogCreatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DogCreatorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DogCreatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
