import { DogCard } from './../../../../../models/dogs/dog-card.model';
import { DogService } from './../../../../../core/services/models/dog.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { catchError, flatMap, map, mergeMap, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { exists } from 'src/app/core/services/utility/utility.model';

enum ProfileOptions {
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
    private dogService: DogService
  ) {}


    dogCard: Observable<DogCard> = this.getDogCard().pipe(
      tap(dogCard => {
        dogCard.images.push(dogCard.images[0]);
        dogCard.images.push(dogCard.images[0])
        dogCard.images.push(dogCard.images[0])
        dogCard.images.push(dogCard.images[0])
        dogCard.images.push(dogCard.images[0])

        console.log(dogCard.images);
        dogCard.reviews.push(dogCard.reviews[0])
        dogCard.reviews.push(dogCard.reviews[0])
        dogCard.reviews.push(dogCard.reviews[0])
        dogCard.reviews.push(dogCard.reviews[0])
        dogCard.reviews.push(dogCard.reviews[0])


      },
      error => this.router.navigate(['/home/dashboard']))
    );
    option = ProfileOptions.WALKS;
    isGalleryOpen = false;
    selectedPhoto: string = '';

  ngOnInit(): void {
    console.log(this.option, this.option === this.options.WALKS)
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
        return this.dogService.getDog(id);
      })
    );
  }

}
