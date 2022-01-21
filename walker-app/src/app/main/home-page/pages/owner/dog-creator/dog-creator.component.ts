import { NotificationService } from 'src/app/core/services/utility/notification.service';
import { DogService } from './../../../../../core/services/models/dog.service';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ImageService } from 'src/app/core/services/models/image.service';

@Component({
  selector: 'ktbz-dog-creator',
  templateUrl: './dog-creator.component.html',
})
export class DogCreatorComponent implements OnInit {
  constructor(
    private builder: FormBuilder,
    private ref: ChangeDetectorRef,
    private dogService: DogService,
    private imageService: ImageService,
    private notification: NotificationService
  ) {}

  selectedFile: string = ' ';
  tempImage?: string;

  cardName?: string;
  cardCharacteristic?: string;
  cardDuration?: string;
  cardIntensity?: string;
  cardDescription?: string;

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

        let dogId = res.message;
        let dogPhoto = new FormData();
        dogPhoto.append('imageFile', this.selectedFile);
        this.imageService.uploadDogPhoto(dogPhoto, dogId).subscribe(
          (res) => {
            this.notification.dispatchNotification(true, 'Dodano zwierzaka', false);
            this.dogForm.reset();
          },
          (err) => this.notification.dispatchNotification(true, err.error, true)
        );
      },
      (err) => this.notification.dispatchNotification(true, err.error, true)
    );
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
