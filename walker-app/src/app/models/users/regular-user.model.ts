import { Role } from "../enums/role.model";

// export interface UserRole{
//   id: string;
//   role: Role;
// }

export interface RegularUser {
  id: string;
  username: string;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  birthdate: Date;
  city: string;
  avatar: string;
  description: string;
  gender: string;
  roles: Role[];
  isActive: boolean;
  isSubscribed: boolean;
}
