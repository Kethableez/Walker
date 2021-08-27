import { UserInfo } from "../users/user-info.model";

export interface PastWalkCard {
  walkId: string;
  isSitterReviewed: boolean;
  isDogReviewed: boolean;
  walkDateTime: Date;
  dogName: string;
  dogPhoto: string;
  sitterInfo: UserInfo;
}
