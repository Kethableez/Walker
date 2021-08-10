import { WalkCard } from './../../../../../models/walks/walk-card.model';
import { SitterService } from './../../../../../core/services/models/sitter.service';
import { Role } from './../../../../../models/enums/role.model';
import { TokenStorageService } from 'src/app/core/services/auth/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { OwnerService } from 'src/app/core/services/models/owner.service';
import { PastWalkCard } from 'src/app/models/walks/past-walk-card.model';

@Component({
  selector: 'ktbz-history',
  templateUrl: './history.component.html',
})
export class HistoryComponent implements OnInit {
  constructor(
    private tokenService: TokenStorageService,
    private sitterService: SitterService,
    private ownerService: OwnerService
  ) {}

  roleView: Role = Role.ROLE_USER;
  walkHistory: WalkCard[] = [];
    ownerWalkHistory: PastWalkCard[] = [];

  ngOnInit(): void {
    this.tokenService.getRole().forEach((role) => {
      if (role === 'ROLE_OWNER') this.roleView = Role.ROLE_OWNER;
      else if (role === 'ROLE_SITTER') this.roleView = Role.ROLE_SITTER;
    });


    if (this.roleView === Role.ROLE_SITTER) {
      this.sitterService.getHistory().subscribe(
        (res) => (this.walkHistory = res)
      );
    }

    else if (this.roleView === Role.ROLE_OWNER) {
      this.ownerService.getHistory().subscribe(
        (res) => this.ownerWalkHistory = res
      )
    }
  }
}
