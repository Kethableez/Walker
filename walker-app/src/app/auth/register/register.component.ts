import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/_services/auth.service';

const ADMIN_TOKEN = '05da579b-cafe-4395-8eeb-88826dfd6cc9';
@Component({
  selector: 'ktbz-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

export class RegisterComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private builder: FormBuilder,
    private router: Router,
    private auth: AuthService) { }

  terms: boolean = false;
  subscription: boolean = false;
  isAdminRegistration: boolean = false;

  registerForm = this.builder.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    username: ['', Validators.required],
    email: ['', Validators.required],
    password: ['', Validators.required],
    birthdate: ['', Validators.required],
    gender: ['', Validators.required],
    role: ['', Validators.required],
    isSubscribed: [false]
  })

  token!: string | null;

  ngOnInit(): void {
    this.token = this.route.snapshot.paramMap.get('token');
    if (this.token != undefined) {
      if (this.token === ADMIN_TOKEN) {
        this.isAdminRegistration = true
        this.registerForm.patchValue({
          'role': 'ROLE_USER',
        })
      }
      else this.router.navigate(['register'], { relativeTo: this.route.parent });
    };
  }

  checkboxClicked(event: MouseEvent) {
    var target = event.target as HTMLElement
    switch (target.id) {
      case 'term':
        this.terms ? this.terms = false : this.terms = true;
        break;

      case 'subscription':
        this.subscription ? this.subscription = false : this.subscription = true;
        this.registerForm.patchValue({
          'isSubscribed': this.subscription,
        })
        break;
    }
  }

  registerUser(): void {
    this.auth.registerUser(this.registerForm.value, this.token).subscribe(
      res => console.log(res),
      err => console.log(err)
    )
  }
}
