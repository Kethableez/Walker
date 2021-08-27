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

  dogForm = this.builder.group({
    name: ['', Validators.required],
    dogBreed: ['', Validators.required],
    dogPhoto: ['', Validators.required],
    dogType: ['', Validators.required],
    characteristic: ['', [Validators.required, Validators.maxLength(255)]],
    walkDuration: ['', [Validators.required, Validators.pattern('[0-9]{2}:[0-9]{2}')]],
    walkIntensity: ['', Validators.required],
    walkDescription: ['', [Validators.required, Validators.maxLength(255)]],
  });

  // Field getters
  get name() {
    return this.dogForm.get('name');
  }

  get dogBreed() {
    return this.dogForm.get('dogBreed');
  }

  get dogPhoto() {
    return this.dogForm.get('dogPhoto');
  }

  get dogType() {
    return this.dogForm.get('dogType');
  }

  get characteristic() {
    return this.dogForm.get('characteristic');
  }

  get walkDuration() {
    return this.dogForm.get('walkDuration');
  }

  get walkIntensity() {
    return this.dogForm.get('walkIntensity');
  }

  get walkDescription() {
    return this.dogForm.get('walkDescription');
  }

  // Validation getters
  get isNameInvalid(): boolean {
    return this.name!.invalid && this.name!.touched;
  }

  get isDogBreedInvalid(): boolean {
    return this.dogBreed!.invalid && this.dogBreed!.touched;
  }

  get isDogPhotoInvalid(): boolean {
    return this.dogPhoto!.invalid && this.dogPhoto!.touched;
  }
  get isDogTypeInvalid(): boolean {
    return this.dogType!.invalid && this.dogType!.touched;
  }
  get isCharacteristicInvalid(): boolean {
    return this.characteristic!.invalid && this.characteristic!.touched;
  }
  get isWalkDurationInvalid(): boolean {
    return this.walkDuration!.invalid && this.walkDuration!.touched;
  }
  get isWalkIntensityInvalid(): boolean {
    return this.walkIntensity!.invalid && this.walkIntensity!.touched;
  }
  get isWalkDescriptionInvalid(): boolean {
    return this.walkDescription!.invalid && this.walkDescription!.touched;
  }

  // Validator
  checkFieldValidation() {
    return (
      this.isNameInvalid ||
      this.isDogBreedInvalid ||
      this.isDogPhotoInvalid ||
      this.isDogTypeInvalid ||
      this.isCharacteristicInvalid ||
      this.isWalkDurationInvalid ||
      this.isWalkIntensityInvalid ||
      this.isWalkDescriptionInvalid
    );
  }

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
          (res) => {
            this.isMessageBoxVisible = true;
            this.dogForm.reset();
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
        dogPhoto: e.target.files[0].name,
      });

      var reader = new FileReader();
      reader.readAsDataURL(e.target.files[0]);
      reader.onload = (event: any) => {
        this.tempImage = event.target.result;
      };
    }
  }
}
