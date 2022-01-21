import { Role } from "../enums/role.model";

export interface UserCard {
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
