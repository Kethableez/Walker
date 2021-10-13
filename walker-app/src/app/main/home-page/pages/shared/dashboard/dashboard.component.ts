import { Role } from 'src/app/models/enums/role.model';
import { CurrentUserStoreService } from './../../../../../core/services/store/current-user-store.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ktbz-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {

  constructor(private userStore: CurrentUserStoreService) { }

  userRole = this.userStore.role;

  ngOnInit(): void {
  }

  get role() {
    return Role;
  }
}
