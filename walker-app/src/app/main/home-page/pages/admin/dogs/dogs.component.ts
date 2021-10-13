import { Component, OnInit } from '@angular/core';
import { tap } from 'rxjs/operators';
import { AdminService } from 'src/app/core/services/models/admin.service';

@Component({
  selector: 'ktbz-dogs',
  templateUrl: './dogs.component.html'
})
export class DogsComponent implements OnInit {

  constructor(private adminService: AdminService) { }

  dogs = this.adminService.dogList.pipe(tap(d => console.log(d)));
  walks = this.adminService.walkList.pipe(tap(d => console.log(d)));
  switchButton = false;

  ngOnInit(): void {
  }

  click() {
    this.switchButton = !this.switchButton;
  }
}
