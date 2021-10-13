import { Role } from 'src/app/models/enums/role.model';
export interface UserWithInfo {
  id: string;
  username: string;
  firstName: string;
  lastName: string;
  avatar: string;
  mainRole: Role;
  active: boolean;
  blocked: boolean;
  banned: boolean;
}
