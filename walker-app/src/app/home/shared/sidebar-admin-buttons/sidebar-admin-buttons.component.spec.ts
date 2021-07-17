import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarAdminButtonsComponent } from './sidebar-admin-buttons.component';

describe('SidebarAdminButtonsComponent', () => {
  let component: SidebarAdminButtonsComponent;
  let fixture: ComponentFixture<SidebarAdminButtonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SidebarAdminButtonsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SidebarAdminButtonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
