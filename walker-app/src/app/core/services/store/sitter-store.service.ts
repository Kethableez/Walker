import { CurrentUserStoreService } from './current-user-store.service';
import { Injectable } from "@angular/core";
import { WalkInfo } from 'src/app/models/walks/walk-info.model';
import { PastWalkInfo } from './../../../models/walks/past-walk-info.model';

const INCOMING_WALKS = 'incoming_walks'
const PAST_WALKS = 'past_walks'
const REVIEWS = 'reviews'
const FAVOURITE = 'favourite'

@Injectable({
  providedIn: 'root',
})
export class SitterStoreService {

  constructor (private userStore: CurrentUserStoreService) {}

  saveIncomingWalks(walks: WalkInfo[]) {
    window.sessionStorage.removeItem(INCOMING_WALKS);
    window.sessionStorage.setItem(INCOMING_WALKS, JSON.stringify(walks));
  }

  savePastWalks(walks: PastWalkInfo[]) {
    window.sessionStorage.removeItem(PAST_WALKS);
    window.sessionStorage.setItem(PAST_WALKS, JSON.stringify(walks));
  }

  saveEnrollAction(walk: WalkInfo) {
    const walks = this.incomingWalks;
    walks.push(this.prepareWalkForEnroll(walk));
    this.saveIncomingWalks(walks);
  }

  saveDisenrollAction(walk: WalkInfo) {
    const walks = this.incomingWalks.filter(w => w.id !== walk.id);
    this.prepareWalkForDisenroll(walk);
    this.saveIncomingWalks(walks);
  }

  get incomingWalks() {
    return JSON.parse(sessionStorage.getItem(INCOMING_WALKS) as string) as WalkInfo[];
  }

  get pastWalks() {
    return JSON.parse(sessionStorage.getItem(PAST_WALKS) as string) as PastWalkInfo[];
  }

  private prepareWalkForEnroll(walk: WalkInfo) {
    walk.isBooked = true;
    walk.sitterId = this.userStore.regularUser.id
    return walk;
  }

  private prepareWalkForDisenroll(walk: WalkInfo) {
    walk.isBooked = false;
    walk.sitterId = '';
  }
}
