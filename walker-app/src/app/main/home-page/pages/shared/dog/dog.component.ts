import { DogCard } from './../../../../../models/dogs/dog-card.model';
import { DogService } from './../../../../../core/services/models/dog.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { catchError, flatMap, map, mergeMap, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { WalkCard } from 'src/app/models/walks/walk-card.model';
import { WalkService } from 'src/app/core/services/models/walk.service';

enum ProfileOptions {
  WALK = 'WALK',
  WALKS = 'WALKS',
  IMAGES = 'IMAGES',
  REVIEWS = 'REVIEWS'
}
@Component({
  selector: 'ktbz-dog',
  templateUrl: './dog.component.html',
})
export class DogComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private dogService: DogService,
    private walkService: WalkService
  ) {}


  isWalkViewEnabled: boolean = false;
  option = ProfileOptions.WALKS;
    dogCard: Observable<DogCard> = this.getDogCard().pipe(
      tap(dogCard => {
      },
      error => this.router.navigate(['/home/dashboard']))
    );
    walkCard?: Observable<WalkCard>;
    //   tap(
    //     () => {
    //       console.log('walk');
    //       this.isWalkViewEnabled = true;
    //       this.option = ProfileOptions.WALK;
    //     },
    //     () => this.isWalkViewEnabled = false
    //   )
    // );
    isGalleryOpen = false;
    selectedPhoto: string = '';

  ngOnInit(): void {
    // console.log(this.option, this.option === this.options.WALKS)
    console.log(this.route.snapshot.paramMap.get('walkId'));
  }

  get options() {
    return ProfileOptions;
  }

  toggleGallery(value: boolean, selectedPhoto?: string): void {
    this.isGalleryOpen = value;
    if (selectedPhoto) this.selectedPhoto = selectedPhoto;
  }

  changeOption(option: ProfileOptions) {
    this.option = option;
  }

  private getDogCard(): Observable<DogCard> {
    return this.route.paramMap.pipe(
      mergeMap(paraMap => {
        const id = paraMap.get('id') as string;
        const walkId = paraMap.get('walkId');
        if (walkId) {
          this.isWalkViewEnabled = true;
          this.option = ProfileOptions.WALK;
          this.walkCard = this.walkService.getWalk(walkId);
        }
        return this.dogService.getDog(id);
      })
    );
  }

  private getWalkCard(): Observable<WalkCard> {
    return this.route.paramMap.pipe(
      mergeMap(paraMap => {
        const walkId = paraMap.get('walkId') as string;
        return this.walkService.getWalk(walkId);
      })
    )
  }

}
