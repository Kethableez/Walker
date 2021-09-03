import { DogInfo } from './../../../../../models/dogs/dog-info.model';
import { DogCard } from './../../../../../models/dogs/dog-card.model';
import { Component, OnInit } from '@angular/core';
import { OwnerService } from 'src/app/core/services/models/owner.service';

@Component({
  selector: 'ktbz-owner-dogs',
  templateUrl: './owner-dogs.component.html'
})
export class OwnerDogsComponent implements OnInit {

  constructor(private ownerService: OwnerService) { }

  dogs: DogInfo[] = [];

  ngOnInit(): void {
    this.ownerService.getDogs().subscribe(
      (res: DogInfo[]) => this.dogs = res
    );
  }

}
