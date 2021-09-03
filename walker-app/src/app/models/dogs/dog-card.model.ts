import { WalkInfo } from './../walks/walk-info.model';
import { UserInfo } from './../users/user-info.model';
import { Dog } from './dog.model';
import { DogReviewCard } from '../reviews/dog-review-card.model';

export interface DogCard {
  dog: Dog;
  owner: UserInfo;
  reviews: DogReviewCard[];
  incomingWalks: WalkInfo[];
  images: string[];
}
