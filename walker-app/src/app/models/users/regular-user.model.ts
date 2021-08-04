import { Role } from "../enums/role.model";

export interface RegularUser {
  id: string;
  username: string;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  birthdate: Date;
  avatar: string;
  gender: string;
  roles: Role[];
  isActive: boolean;
  isSubscribed: boolean;
}
