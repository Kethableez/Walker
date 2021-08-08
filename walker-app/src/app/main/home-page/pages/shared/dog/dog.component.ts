import { DogCard } from './../../../../../models/dogs/dog-card.model';
import { DogService } from './../../../../../core/services/models/dog.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ktbz-dog',
  templateUrl: './dog.component.html',
})
export class DogComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private dogService: DogService
  ) {}

    dogId!: string | null;
    dogCard?: DogCard;

  ngOnInit(): void {
    this.dogId = this.route.snapshot.paramMap.get('id');
    if(this.dogId != null) {
      this.dogService.getDog(this.dogId).subscribe(
        (response: DogCard) => {this.dogCard = response;
        console.log(this.dogCard);
      }
      )
    }
  }
}
