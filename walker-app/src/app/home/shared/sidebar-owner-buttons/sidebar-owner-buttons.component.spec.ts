import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarOwnerButtonsComponent } from './sidebar-owner-buttons.component';

describe('SidebarOwnerButtonsComponent', () => {
  let component: SidebarOwnerButtonsComponent;
  let fixture: ComponentFixture<SidebarOwnerButtonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SidebarOwnerButtonsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SidebarOwnerButtonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
