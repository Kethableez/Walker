import { NotificationService } from 'src/app/core/services/utility/notification.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/core/services/models/user.service';

@Component({
  selector: 'ktbz-problem-report-form',
  templateUrl: './problem-report-form.component.html',
})
export class ProblemReportFormComponent implements OnInit {
  constructor(
    private builder: FormBuilder,
    private userService: UserService,
    private notification: NotificationService
  ) {}

  reportForm = this.builder.group({
    reportBody: ['', Validators.required],
  });

  ngOnInit(): void {}

  submitReport() {
    this.userService
      .postReport(this.reportForm.get('reportBody')?.value)
      .subscribe(
        (response) =>
          this.notification.dispatchNotification(true, response.message, false),
        (error) =>
          this.notification.dispatchNotification(true, error.error, true)
      );
  }
}
