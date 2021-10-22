import { Role } from "../enums/role.model";

export interface UserCard {
  id: string;
  username: string;
  firstName: string;
  lastName: string;
  birthdate: string;
  city: string;
  avatar: string;
  gender: string;
  description: string;
  roles: Role[];
}
