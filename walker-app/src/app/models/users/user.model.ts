import { Role } from './../enums/role.model';
import { Dog } from "../dogs/dog.model";
import { Walk } from "../walks/walk.model";
import { RegularUser } from "./regular-user.model";

export interface User {
  id: string;
  username: string;
  firstName: string;
  lastName: string;
  birthdate: string;
  zipCode: string;
  city: string;
  districtCode: string;
  regionCode: string;
  avatar: string;
  gender: string;
  description: string;
  roles: Role[];
}
