import { Component, OnInit } from '@angular/core';
import { AdminService, ReportStatus } from 'src/app/core/services/models/admin.service';

@Component({
  selector: 'ktbz-bug-report',
  templateUrl: './bug-report.component.html'
})
export class BugReportComponent implements OnInit {

  constructor(private adminService: AdminService) { }

  newReports = this.adminService.getReportByStatus(ReportStatus.NEW);
  inProgressReports = this.adminService.getReportByStatus(ReportStatus.IN_PROGRESS);
  doneReports = this.adminService.getReportByStatus(ReportStatus.DONE);

  ngOnInit(): void {
  }

}
