import { DogInfo } from './../../../../../models/dogs/dog-info.model';
import { WalkService } from './../../../../../core/services/models/walk.service';
import { FormBuilder, Validator, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActionResponse } from 'src/app/models/action-response.model';
import { OwnerService } from 'src/app/core/services/models/owner.service';
import { DogCard } from 'src/app/models/dogs/dog-card.model';

@Component({
  selector: 'ktbz-walk-planner',
  templateUrl: './walk-planner.component.html',
})
export class WalkPlannerComponent implements OnInit {
  constructor(
    private builder: FormBuilder,
    private walkService: WalkService,
    private ownerService: OwnerService) { }

  response?: ActionResponse;
  isMessageBoxVisible = false;
  ownerDogs: DogInfo[] = [];

  walkForm = this.builder.group({
    address: ['', Validators.required],
    city: ['', Validators.required],
    dogId: ['', Validators.required],
    walkDateTime: ['', Validators.required],
    walkDescription: ['', Validators.maxLength(255)],
    walkLat: ['69.99', Validators.required],
    walkLon: ['42.00', Validators.required],
  });

  get address() { return this.walkForm.get('address') };
  get city() { return this.walkForm.get('city') };
  get walkDateTime() { return this.walkForm.get('walkDateTime') };
  get walkDescription() { return this.walkForm.get('walkDescription') };

  get isAddressInvalid() { return this.address!.invalid && this.address!.touched };
  get isCityInvalid() { return this.city!.invalid && this.city!.touched };
  get isWalkDateTimeInvalid() { return this.walkDateTime!.invalid && this.walkDateTime!.touched };

  checkFieldValidation() {
    return (
      this.isAddressInvalid ||
      this.isCityInvalid ||
      this.isWalkDateTimeInvalid
    );
  }

  ngOnInit(): void {
    this.ownerService.getDogs().subscribe(
      (response: DogInfo[]) => this.ownerDogs = response
    )
  }

  selectDog(id: string) {
    this.walkForm.patchValue({
      dogId: id,
    });
  }

  createWalk() {
    this.walkService.createWalk(this.walkForm.value).subscribe(
      (res) => {
        this.response = {
          message: res.message,
          isSuccess: true,
        };
        this.isMessageBoxVisible = true;
        this.walkForm.get('address')?.reset()
        this.walkForm.get('city')?.reset()
        this.walkForm.get('dogId')?.reset()
        this.walkForm.get('walkDateTime')?.reset()
        this.walkForm.get('walkDescription')?.reset()
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
