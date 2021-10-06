import { DogInfo } from "../dogs/dog-info.model";
import { WalkInfo } from "../walks/walk-info.model";
import { User } from "./user.model";

export interface OwnerData {
  user: User;
  dogs: DogInfo[];
  plannedWalks: WalkInfo[];
  pastWalks: WalkInfo[];
  dogImages: string[];
}
