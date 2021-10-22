import { UserInfo } from './../users/user-info.model';
import { WalkCard } from '../walks/walk-card.model';
export interface DogReviewCard {
  id: string;
  sitter: UserInfo;
  walkCard: WalkCard;
  photo: String;
  reviewBody: String;
  reviewDate: Date;
}
