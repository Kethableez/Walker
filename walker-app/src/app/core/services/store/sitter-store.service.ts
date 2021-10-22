import { SitterReviewCard } from './../../../models/reviews/sitter-review-card.model';
import { CurrentUserStoreService } from './current-user-store.service';
import { Injectable } from "@angular/core";
import { WalkInfo } from 'src/app/models/walks/walk-info.model';
import { PastWalkInfo } from './../../../models/walks/past-walk-info.model';
import { SitterData } from 'src/app/models/users/sitter.model';
import { WalkCard } from 'src/app/models/walks/walk-card.model';

const INCOMING_WALKS = 'incoming_walks'
const PAST_WALKS = 'past_walks'
const REVIEWS = 'reviews'
const SITTER_DATA = 'sitter_data'
const FAVOURITE = 'favourite'

@Injectable({
  providedIn: 'root',
})
export class SitterStoreService {

  constructor (private userStore: CurrentUserStoreService) {}

  saveIncomingWalks(walks: WalkCard[]) {
    window.sessionStorage.removeItem(INCOMING_WALKS);
    window.sessionStorage.setItem(INCOMING_WALKS, JSON.stringify(walks));
  }

  savePastWalks(walks: WalkCard[]) {
    window.sessionStorage.removeItem(PAST_WALKS);
    window.sessionStorage.setItem(PAST_WALKS, JSON.stringify(walks));
  }

  saveEnrollAction(walk: WalkCard) {
    const walks = this.incomingWalks;
    walks.push(this.prepareWalkForEnroll(walk));
    this.saveIncomingWalks(walks);
  }

  saveReviews(reviews: SitterReviewCard) {
    window.sessionStorage.removeItem(REVIEWS);
    window.sessionStorage.setItem(REVIEWS, JSON.stringify(reviews));
  }

  saveSitterData(sitterData: SitterData) {
    window.sessionStorage.removeItem(SITTER_DATA);
    window.sessionStorage.setItem(SITTER_DATA, JSON.stringify(sitterData))
  }

  saveDisenrollAction(walkCard: WalkCard) {
    const walks = this.incomingWalks.filter(w => w.walk.id !== walkCard.walk.id);
    this.prepareWalkForDisenroll(walkCard);
    this.saveIncomingWalks(walks);
  }

  get sitterData() {
    return JSON.parse(sessionStorage.getItem(SITTER_DATA) as string) as SitterData;
  }

  get incomingWalks() {
    return JSON.parse(sessionStorage.getItem(INCOMING_WALKS) as string) as WalkCard[];
  }

  get pastWalks() {
    return JSON.parse(sessionStorage.getItem(PAST_WALKS) as string) as PastWalkInfo[];
  }

  private prepareWalkForEnroll(walkCard: WalkCard) {
    walkCard.walk.booked = true;
    walkCard.walk.sitterId = this.userStore.regularUser.id
    return walkCard;
  }

  private prepareWalkForDisenroll(walkCard: WalkCard) {
    walkCard.walk.booked = false;
    walkCard.walk.sitterId = '';
  }
}
