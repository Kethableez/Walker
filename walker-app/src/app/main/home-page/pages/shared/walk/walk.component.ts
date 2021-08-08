import { WalkService } from './../../../../../core/services/models/walk.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DogService } from 'src/app/core/services/models/dog.service';
import { WalkCard } from './../../../../../models/walks/walk-card.model';

@Component({
  selector: 'ktbz-walk',
  templateUrl: './walk.component.html'
})
export class WalkComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private walkService: WalkService
  ) {}

    walkId!: string | null;
    walkCard?: WalkCard;

  ngOnInit(): void {
    this.walkId = this.route.snapshot.paramMap.get('id');
    if(this.walkId != null) {
      this.walkService.getWalk(this.walkId).subscribe(
        (response: WalkCard) => {this.walkCard = response;
        console.log(this.walkCard);
      }
      )
    }
  }

}
