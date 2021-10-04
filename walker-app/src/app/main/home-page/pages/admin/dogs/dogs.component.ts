import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/core/services/models/admin.service';

@Component({
  selector: 'ktbz-dogs',
  templateUrl: './dogs.component.html'
})
export class DogsComponent implements OnInit {

  constructor(private adminService: AdminService) { }

  dogs = this.adminService.dogList;
  walks = this.adminService.walkList;
  switchButton = false;

  ngOnInit(): void {
  }

  click() {
    this.switchButton = !this.switchButton;
  }
}
