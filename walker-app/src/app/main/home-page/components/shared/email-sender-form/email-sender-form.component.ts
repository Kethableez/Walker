import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'ktbz-email-sender-form',
  templateUrl: './email-sender-form.component.html',
})
export class EmailSenderFormComponent implements OnInit {
  @Input()
  userId: string = '';

  constructor(private builder: FormBuilder) { }

  emailForm = this.builder.group({
    userId: ['', Validators.required],
    emailSubject: ['', Validators.required],
    emailBody: ['', Validators.required]
  })

  ngOnInit(): void {
    this.emailForm.patchValue({
      userId: this.userId
    })
  }

  sendEmail() {
  }

}
