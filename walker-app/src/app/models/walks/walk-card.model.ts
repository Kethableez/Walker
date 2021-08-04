import { Dog } from "../dogs/dog.model";
import { UserInfo } from "../users/user-info.model";
import { Walk } from "./walk.model";

export interface WalkCard {
  walk: Walk;
  dog: Dog;
  userInfo: UserInfo;
}
