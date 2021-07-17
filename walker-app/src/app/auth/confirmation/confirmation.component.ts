import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/_services/auth.service';

@Component({
  selector: 'ktbz-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit {

  token!: string;

  constructor(private route: ActivatedRoute,
    private authService: AuthService,
    private builder: FormBuilder) { }

    code = new FormControl('');

  ngOnInit(): void {
    this.token = this.route.snapshot.paramMap.get('token') as string;
  }

  confirmAccount(): void {
    console.log(this.token, this.code.value);
    this.authService.confirmToken(this.token, this.code.value).subscribe(
      res => console.log(res),
      err => console.log(err)
    )
  }

}
