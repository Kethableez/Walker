import { User } from 'src/app/models/users/user.model';
import { SitterReviewCard } from '../reviews/sitter-review-card.model';

export interface SitterData {
  sitter: User;
  rating: number;
  reviews: SitterReviewCard[];
  images: string[];
}
