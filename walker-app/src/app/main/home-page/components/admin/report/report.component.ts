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

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
  }

  changeStatus(reportId: string, status: ReportStatus) {
    this.adminService.changeReportStatus(reportId, status).subscribe();
  }

  get reportStatus() {
    return ReportStatus;
  }
}
