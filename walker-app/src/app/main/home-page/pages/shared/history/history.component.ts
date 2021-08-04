import { WalkCard } from './../../../../../models/walks/walk-card.model';
import { SitterService } from './../../../../../core/services/models/sitter.service';
import { Role } from './../../../../../models/enums/role.model';
import { TokenStorageService } from 'src/app/core/services/auth/token-storage.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ktbz-history',
  templateUrl: './history.component.html',
})
export class HistoryComponent implements OnInit {
  constructor(
    private tokenService: TokenStorageService,
    private SitterService: SitterService
  ) {}

  roleView: Role = Role.ROLE_USER;
  walkHistory: WalkCard[] = [];

  ngOnInit(): void {
    this.tokenService.getRole().forEach((role) => {
      if (role === 'ROLE_OWNER') this.roleView = Role.ROLE_OWNER;
      else if (role === 'ROLE_SITTER') this.roleView = Role.ROLE_SITTER;
    });

    console.log(this.roleView);

    if (this.roleView === Role.ROLE_SITTER) {
      this.SitterService.getHistory().subscribe(
        (res) => (this.walkHistory = res),
        (err) => console.log(err.error)
      );
    }
  }
}
