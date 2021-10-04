import { WalkInfo } from './../walks/walk-info.model';
import { DogInfo } from './../dogs/dog-info.model';
import { UserInfo } from './../users/user-info.model';
export interface SitterReviewCard {
  id: string;
  owner: UserInfo;
  dog: DogInfo;
  walk: WalkInfo;
  rating: number;
  reviewBody: string;
  reviewDate: string;
}
