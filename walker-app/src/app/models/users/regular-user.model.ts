import { Role } from "../enums/role.model";

export interface UserRole{
  id: string;
  role: Role;
}

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
  roles: UserRole[];
  isActive: boolean;
  isSubscribed: boolean;
}
