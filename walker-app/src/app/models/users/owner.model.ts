import { DogCard } from "../dogs/dog-card.model";
import { WalkInfo } from "../walks/walk-info.model";
import { User } from "./user.model";

export interface OwnerData {
  user: User;
  dogs: DogCard[];
  dogImages: string[];
}
