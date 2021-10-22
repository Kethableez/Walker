import { Dog } from "../dogs/dog.model";
import { UserCard } from "../users/user-card.model";
import { Walk } from "./walk.model";

export interface WalkCard {
  walk: Walk;
  dog: Dog;
  userCard: UserCard;
}
