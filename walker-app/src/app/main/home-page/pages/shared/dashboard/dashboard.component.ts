import { Role } from 'src/app/models/enums/role.model';
import { CurrentUserStoreService } from './../../../../../core/services/store/current-user-store.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { WalkCard } from 'src/app/models/walks/walk-card.model';
import { SitterService } from 'src/app/core/services/models/sitter.service';
import { OwnerService } from 'src/app/core/services/models/owner.service';
import { UserService } from 'src/app/core/services/models/user.service';
import { Notification } from 'src/app/models/notification.model';
import { map } from 'rxjs/operators';
import { Activity } from 'src/app/models/walks/activity.model';
import { AdminService, ReportStatus } from 'src/app/core/services/models/admin.service';

@Component({
  selector: 'ktbz-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {

  constructor(
    private userStore: CurrentUserStoreService,
    private sitterService: SitterService,
    private ownerService: OwnerService,
    private adminService: AdminService,
    private userService: UserService) { }

  userRole = this.userStore.role;
  incomingWalks: Observable<WalkCard[]> = this.initIncomingWalks();
  notifications: Observable<Notification[]> = this.userService.getNotifications().pipe(
    map(arr => arr.reverse())
  );
  acitvities: Observable<Activity[]> = this.adminService.activity;
  reports: Observable<any[]> = this.adminService.getReportByStatus(ReportStatus.NEW);

  ngOnInit(): void {
  }

  markAsRead(notificationId: string) {
    this.userService.markAsRead(notificationId).subscribe();
  }

  private initIncomingWalks(): Observable<WalkCard[]> {
    if(this.userRole === Role.ROLE_SITTER) return this.sitterService.getWalks();
    else return this.ownerService.getWalks();
  }

  get role() {
    return Role;
  }
}
