import { NotificationService } from 'src/app/core/services/utility/notification.service';
import { Component, Input, OnInit } from '@angular/core';
import { AdminService, ReportStatus } from 'src/app/core/services/models/admin.service';
import { Report } from 'src/app/models/report.model';

@Component({
  selector: 'ktbz-report',
  templateUrl: './report.component.html'
})
export class ReportComponent implements OnInit {

  @Input()
  report!: Report;

  @Input()
  isDashboardView = true;

  constructor(private adminService: AdminService, private notification: NotificationService) { }

  ngOnInit(): void {
  }

  changeStatus(reportId: string, status: ReportStatus) {
    this.adminService.changeReportStatus(reportId, status).subscribe(
      response => this.notification.dispatchNotification(true, response.message, false),
      error => this.notification.dispatchNotification(true, error.error, true)
    );
  }

  get reportStatus() {
    return ReportStatus;
  }
}
