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
  ownerDogs: DogCard[] = [];

  walkForm = this.builder.group({
    address: ['', Validators.required],
    city: ['', Validators.required],
    dogId: ['', Validators.required],
    walkDateTime: ['', Validators.required],
    walkDescription: [''],
    walkLat: ['69.99', Validators.required],
    walkLon: ['42.00', Validators.required],
  });

  ngOnInit(): void {
    this.ownerService.getDogs().subscribe(
      (response: DogCard[]) => this.ownerDogs = response
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
