import { ActionResponse } from '../../../models/action-response.model';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { ImageService } from 'src/app/core/services/models/image.service';
import { MustMatch } from './password.validator';

const ADMIN_TOKEN = '05da579b-cafe-4395-8eeb-88826dfd6cc9';

@Component({
  selector: 'ktbz-register',
  templateUrl: './register.component.html',
})
export class RegisterComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private builder: FormBuilder,
    private router: Router,
    private auth: AuthService,
    private imageService: ImageService
  ) {}

  registerForm = this.builder.group(
    {
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: [
        '',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(20),
        ],
      ],
      email: [
        '',
        [
          Validators.required,
          Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
        ],
      ],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(20),
        ],
      ],
      confirmPassword: ['', Validators.required],
      birthdate: ['', Validators.required],
      city: ['', Validators.required],
      gender: ['', Validators.required],
      avatar: ['', Validators.required],
      role: ['', Validators.required],
      isSubscribed: [false],
    },
    {
      validator: MustMatch('password', 'confirmPassword'),
    }
  );

  get firstName() {
    return this.registerForm.get('firstName');
  }
  get lastName() {
    return this.registerForm.get('lastName');
  }
  get username() {
    return this.registerForm.get('username');
  }
  get email() {
    return this.registerForm.get('email');
  }
  get password() {
    return this.registerForm.get('password');
  }
  get confirmPassword() {
    return this.registerForm.get('confirmPassword');
  }
  get birthdate() {
    return this.registerForm.get('birthdate');
  }

  get city() {
    return this.registerForm.get('city');
  }

  get gender() {
    return this.registerForm.get('gender');
  }
  get avatar() {
    return this.registerForm.get('avatar');
  }
  get role() {
    return this.registerForm.get('role');
  }
  get formControl() {
    return this.registerForm.controls;
  }

  get isFirstNameInvalid(): boolean {
    return this.firstName!.invalid && this.firstName!.touched;
  }

  get isLastNameInvalid(): boolean {
    return this.lastName!.invalid && this.lastName!.touched;
  }

  get isUsernameInvalid(): boolean {
    return this.username!.invalid && this.username!.touched;
  }

  get isEmailInvalid(): boolean {
    return this.email!.invalid && this.email!.touched;
  }

  get isPasswordInvalid(): boolean {
    return this.password!.invalid && this.password!.touched;
  }

  get isConfirmPasswordInvalid(): boolean {
    return this.confirmPassword!.invalid && this.confirmPassword!.touched;
  }

  get isBirthdateInvalid(): boolean {
    return this.birthdate!.invalid && this.birthdate!.touched;
  }

  get isCityInvalid(): boolean {
    return this.city!.invalid && this.city!.touched;
  }

  get isGenderInvalid(): boolean {
    return this.gender!.invalid && this.gender!.touched;
  }

  get isAvatarInvalid(): boolean {
    return this.avatar!.invalid && this.avatar!.touched;
  }

  get isRoleInvalid(): boolean {
    return this.role!.invalid && this.role!.touched;
  }

  checkFieldValidation() {
    return (
      this.isFirstNameInvalid ||
      this.isLastNameInvalid ||
      this.isUsernameInvalid ||
      this.isEmailInvalid ||
      this.isPasswordInvalid ||
      this.isConfirmPasswordInvalid ||
      this.isBirthdateInvalid ||
      this.isCityInvalid ||
      this.isGenderInvalid ||
      this.isAvatarInvalid ||
      this.isRoleInvalid
    );
  }

  terms: boolean = false;
  subscription: boolean = false;
  isAdminRegistration: boolean = false;
  isMessageBoxVisible = false;
  response?: ActionResponse;
  selectedFile: string = ' ';
  tempImage?: string;
  token!: string | null;

  ngOnInit(): void {
    this.token = this.route.snapshot.paramMap.get('token');
    if (this.token != undefined) {
      if (this.token === ADMIN_TOKEN) {
        this.isAdminRegistration = true;
        this.registerForm.patchValue({
          role: 'ROLE_USER',
        });
      } else
        this.router.navigate(['register'], { relativeTo: this.route.parent });
    }
  }

  checkboxClicked(event: MouseEvent) {
    var target = event.target as HTMLElement;
    switch (target.id) {
      case 'term':
        this.terms ? (this.terms = false) : (this.terms = true);
        break;

      case 'subscription':
        this.subscription
          ? (this.subscription = false)
          : (this.subscription = true);
        this.registerForm.patchValue({
          isSubscribed: this.subscription,
        });
        break;
    }
  }

  registerUser(): void {
    this.auth.registerUser(this.registerForm.value, this.token).subscribe(
      (res) => {
        this.response = {
          message: 'Zarejestrowano pomyślnie',
          isSuccess: true,
        };

        let userId = res.message;
        let userPhoto = new FormData();
        userPhoto.append('imageFile', this.selectedFile);
        this.imageService.uploadUserPhoto(userPhoto, userId).subscribe(
          (res) => {
            this.isMessageBoxVisible = true;
            this.registerForm.reset();
          },
          (err) => {
            (this.response = {
              message: err.error,
              isSuccess: true,
            }),
              (this.isMessageBoxVisible = true);
          }
        );
      },
      (err) => {
        this.response = {
          message: err.error,
          isSuccess: false,
        };
        this.isMessageBoxVisible = true;
      }
    );
  }

  closeMessageBox(event: boolean) {
    this.isMessageBoxVisible = false;
  }

  onSelectFile(e: any) {
    if (e.target.files.length > 0) {
      const f = e.target.files[0];
      this.selectedFile = e.target.files[0];
      this.registerForm.patchValue({
        avatar: e.target.files[0].name,
      });

      var reader = new FileReader();
      reader.readAsDataURL(e.target.files[0]);
      reader.onload = (event: any) => {
        this.tempImage = event.target.result;
      };
    }
  }
}
