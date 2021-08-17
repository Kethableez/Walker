import { ActionResponse } from '../../../models/action-response.model';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { ImageService } from 'src/app/core/services/models/image.service';

const ADMIN_TOKEN = '05da579b-cafe-4395-8eeb-88826dfd6cc9';

@Component({
  selector: 'ktbz-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private builder: FormBuilder,
    private router: Router,
    private auth: AuthService,
    private imageService: ImageService) { }

  terms: boolean = false;
  subscription: boolean = false;
  isAdminRegistration: boolean = false;
  isMessageBoxVisible = false;
  response?: ActionResponse;
  selectedFile: string = ' ';
  tempImage?: string;

  registerForm = this.builder.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    username: ['', Validators.required],
    email: ['', Validators.required],
    password: ['', Validators.required],
    birthdate: ['', Validators.required],
    gender: ['', Validators.required],
    avatar: ['', Validators.required],
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
      res => {
        this.response = {
          message: res.message,
          isSuccess: true,
        };

        let userId = res.message;
        let userPhoto = new FormData();
        userPhoto.append('imageFile', this.selectedFile);
        this.imageService.uploadUserPhoto(userPhoto, userId).subscribe(
          res => {
            this.isMessageBoxVisible = true;
            this.registerForm.reset();
          },
          err => {
            (this.response = {
              message: err.error,
              isSuccess: true,
            }),
              (this.isMessageBoxVisible = true);
          }
        )
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

  onSelectFile(e: any) {
    if (e.target.files.length > 0) {
      const f = e.target.files[0];
      this.selectedFile = e.target.files[0];
      this.registerForm.patchValue({
        avatar: e.target.files[0].name
      })

      var reader = new FileReader();
      reader.readAsDataURL(e.target.files[0]);
      reader.onload = (event: any) => {
        this.tempImage = event.target.result;
      }
    }
  }
}
