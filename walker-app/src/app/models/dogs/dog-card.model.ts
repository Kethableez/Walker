import { Walk } from './../walks/walk.model';
import { UserInfo } from './../users/user-info.model';
import { Dog } from './dog.model';
export interface DogCard {
  dog: Dog;
  owner: UserInfo;
  incomingWalks: Walk;
}
