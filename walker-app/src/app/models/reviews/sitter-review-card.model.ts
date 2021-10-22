import { UserInfo } from './../users/user-info.model';
import { WalkCard } from '../walks/walk-card.model';
export interface SitterReviewCard {
  id: string;
  owner: UserInfo;
  walkCard: WalkCard;
  rating: number;
  reviewBody: string;
  reviewDate: string;
}
