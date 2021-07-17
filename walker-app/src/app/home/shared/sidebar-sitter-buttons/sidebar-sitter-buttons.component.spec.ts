import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarSitterButtonsComponent } from './sidebar-sitter-buttons.component';

describe('SidebarSitterButtonsComponent', () => {
  let component: SidebarSitterButtonsComponent;
  let fixture: ComponentFixture<SidebarSitterButtonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SidebarSitterButtonsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SidebarSitterButtonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
