import { UserInfo } from "../users/user-info.model";

export interface PastWalkCard {
  walkId: string;
  walkDateTime: Date;
  dogName: string;
  dogPhoto: string;
  sitterInfo: UserInfo;
}
