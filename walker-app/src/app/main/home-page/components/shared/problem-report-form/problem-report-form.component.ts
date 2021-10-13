import { FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ktbz-problem-report-form',
  templateUrl: './problem-report-form.component.html'
})
export class ProblemReportFormComponent implements OnInit {

  constructor(private builder: FormBuilder) { }

  reportForm = this.builder.group({
    reportBody: ['', Validators.required]
  })

  ngOnInit(): void {
  }

  submitReport() {
    console.log('tehe');
  }
}
