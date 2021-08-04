import { ActionResponse } from './../../../../../models/action-response.model';
import { DogService } from './../../../../../core/services/models/dog.service';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'ktbz-dog-creator',
  templateUrl: './dog-creator.component.html',
})
export class DogCreatorComponent implements OnInit {
  constructor(
    private builder: FormBuilder,
    private ref: ChangeDetectorRef,
    private dogService: DogService
  ) {}

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
    dogPhoto: ['pies.png'],
    dogType: ['', Validators.required],
    characteristic: ['', Validators.required],
    walkDuration: [
      '',
      [Validators.required, Validators.pattern('[0-9]{2}:[0-9]{2}')],
    ],
    walkIntensity: ['', Validators.required],
    walkDescription: ['', Validators.required],
  });

  get name() {
    return this.dogForm.get('name');
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
  }

  closeMessageBox(event: boolean) {
    this.isMessageBoxVisible = false;
  }
}
