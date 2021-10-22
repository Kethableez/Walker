import { Component, OnInit } from '@angular/core';
import { OwnerService } from 'src/app/core/services/models/owner.service';
import { DogCard } from 'src/app/models/dogs/dog-card.model';

@Component({
  selector: 'ktbz-owner-dogs',
  templateUrl: './owner-dogs.component.html'
})
export class OwnerDogsComponent implements OnInit {

  constructor(private ownerService: OwnerService) { }

  dogs: DogCard[] = [];

  ngOnInit(): void {
    this.ownerService.getDogs().subscribe(
      (res: DogCard[]) => this.dogs = res
    );
  }

}
