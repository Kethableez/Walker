import { ActionResponse } from './../../../../../models/action-response.model';
import { DogService } from './../../../../../core/services/models/dog.service';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ImageService } from 'src/app/core/services/models/image.service';
import { formatCurrency } from '@angular/common';

@Component({
  selector: 'ktbz-dog-creator',
  templateUrl: './dog-creator.component.html',
})
export class DogCreatorComponent implements OnInit {
  constructor(
    private builder: FormBuilder,
    private ref: ChangeDetectorRef,
    private dogService: DogService,
    private imageService: ImageService
  ) {}

  selectedFile: string = ' ';
  tempImage?: string;

  cardName?: string;
  cardCharacteristic?: string;
  cardDuration?: string;
  cardIntensity?: string;
  cardDescription?: string;

  response?: ActionResponse;
  isMessageBoxVisible = false;

  // Dodać walidatory w builderze + html
  dogForm = this.builder.group({
    name: ['', Validators.required],
    dogBreed: ['', Validators.required],
    dogPhoto: ['', Validators.required],
    dogType: ['', Validators.required],
    characteristic: ['', Validators.required],
    walkDuration: [
      '',
      [Validators.required, Validators.pattern('[0-9]{2}:[0-9]{2}')],
    ],
    walkIntensity: ['', Validators.required],
    walkDescription: ['', Validators.required],
  });

  ngOnInit(): void {
    this.ref.detectChanges();
  }

  createDog() {
    this.dogService.createDog(this.dogForm.value).subscribe(
      (res) => {
        this.response = {
          message: res.message,
          isSuccess: true,
        };

        let dogId = res.message;
        let dogPhoto = new FormData();
        dogPhoto.append('imageFile', this.selectedFile);
        this.imageService.uploadDogPhoto(dogPhoto, dogId).subscribe(
          res => {
            this.isMessageBoxVisible = true;
            this.dogForm.reset();
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
      (err) => {
        (this.response = {
          message: err.error,
          isSuccess: true,
        }),
          (this.isMessageBoxVisible = true);
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
      this.dogForm.patchValue({
        dogPhoto: e.target.files[0].name
      })

      var reader = new FileReader();
      reader.readAsDataURL(e.target.files[0]);
      reader.onload = (event: any) => {
        this.tempImage = event.target.result;
      }
    }
  }
}
