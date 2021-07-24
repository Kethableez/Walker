import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { ServerResponse } from 'src/app/models/server-response.model';

@Component({
  selector: 'ktbz-confirmation',
  templateUrl: './confirmation.component.html'
})
export class ConfirmationComponent implements OnInit {

  token!: string;
  isMessageBoxVisible = false;
  response?: ServerResponse;

  constructor(private route: ActivatedRoute,
    private authService: AuthService) { }

  code = new FormControl('', Validators.required);

  ngOnInit(): void {
    this.token = this.route.snapshot.paramMap.get('token') as string;
  }

  confirmAccount(): void {
    this.authService.confirmToken(this.token, this.code.value).subscribe(
      res => {
        this.response = {
          'message': res.message,
          'isSuccess': true
        }
        this.isMessageBoxVisible = true;
      },
      err => {
        this.response = {
          'message': err.error,
          'isSuccess': false
        }
        this.isMessageBoxVisible = true;
      }
    )
  }

  closeMessageBox(event: boolean) {
    this.isMessageBoxVisible = false;
  }
}
