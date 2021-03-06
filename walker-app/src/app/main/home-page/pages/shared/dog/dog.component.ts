import { NotificationService } from 'src/app/core/services/utility/notification.service';
import { DogCard } from './../../../../../models/dogs/dog-card.model';
import { DogService } from './../../../../../core/services/models/dog.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { mergeMap, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { WalkCard } from 'src/app/models/walks/walk-card.model';
import { WalkService } from 'src/app/core/services/models/walk.service';
import { CurrentUserStoreService } from 'src/app/core/services/store/current-user-store.service';
import { Role } from 'src/app/models/enums/role.model';

enum ButtonState {
  ENROLL, DISENROLL
}

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
    private walkService: WalkService,
    private userStore: CurrentUserStoreService,
    private notification: NotificationService
  ) { }


  isWalkViewEnabled: boolean = false;
  walkId?: string;

  option = ProfileOptions.WALKS;
  dogCard: Observable<DogCard> = this.getDogCard().pipe(
    tap(
      () => { },
      () => this.router.navigate(['/home/dashboard']))
  );
  walkCard?: Observable<WalkCard>;
  isGalleryOpen = false;
  selectedPhoto: string = '';

  buttonState?: ButtonState;
  isButtonEnabled: boolean = false;
  buttonKey: string = '';

  ngOnInit(): void {

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
        this.walkId = walkId as string;
        if (walkId) {
          this.isWalkViewEnabled = true;
          this.option = ProfileOptions.WALK;
          this.walkCard = this.walkService.getWalk(walkId).pipe(tap(
            card => {
              if (this.userStore.role === Role.ROLE_SITTER) {
                console.log(card.walk.booked, card.walk.sitterId, this.userStore.role, this.userStore.regularUser.id);
                if (!card.walk.booked) this.prepareButton(true, 'Zapisz si??', ButtonState.ENROLL);
                else if (card.walk.booked && card.walk.sitterId === this.userStore.regularUser.id) this.prepareButton(true, 'Wypisz si??', ButtonState.DISENROLL);
                else this.prepareButton(false, '');
              }
              else this.prepareButton(false, '');
            }
          ));
        }
        return this.dogService.getDog(id);
      })
    );
  }

  dispatchAction() {
    if (this.buttonState === ButtonState.ENROLL) {
      this.walkService.enroll(this.walkId as string).subscribe(
        (response) => {
          this.notification.dispatchNotification(true, response.message, false);
          this.prepareButton(true, 'wypisz si??', this.buttonState = ButtonState.DISENROLL);
        },
        (error) => this.notification.dispatchNotification(true, error.error, true)
      )
    }
    else if (this.buttonState === ButtonState.DISENROLL) {
      this.walkService.disenroll(this.walkId as string).subscribe(
        (response) => {
          this.notification.dispatchNotification(true, response.message, false);
          this.prepareButton(true, 'zapisz si??', this.buttonState = ButtonState.ENROLL);
        },
        (error) => this.notification.dispatchNotification(true, error.error, true)
      )
    }
  }

  prepareButton(enabled: boolean, key: string, state?: ButtonState,) {
    this.buttonState = state;
    this.isButtonEnabled = enabled;
    this.buttonKey = key;
  }
}
