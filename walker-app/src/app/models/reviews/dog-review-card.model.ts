import { WalkInfo } from './../walks/walk-info.model';
import { DogInfo } from './../dogs/dog-info.model';
import { UserInfo } from './../users/user-info.model';
export interface DogReviewCard {
  id: string;
  sitter: UserInfo;
  dog: DogInfo;
  walk: WalkInfo;
  photo: String;
  reviewBody: String;
  reviewDate: Date;
}
