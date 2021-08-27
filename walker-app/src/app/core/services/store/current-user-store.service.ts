import { UserService } from 'src/app/core/services/models/user.service';
import { Injectable } from '@angular/core';
import { RegularUser, UserRole } from 'src/app/models/users/regular-user.model';
import { User } from 'src/app/models/users/user.model';
import { Role } from 'src/app/models/enums/role.model';

const CURRENT_USER = 'current_user';
const MAIN_ROLE = 'main_role';
const ROLES = 'user_roles';

@Injectable({
  providedIn: 'root',
})
export class CurrentUserStoreService {
  constructor ( ) { };

  save(user: any) {
    this.saveUser(user);
    this.saveRoles(user);
    this.saveRole(user);
  }

  update(user: any) {
    this.saveUser(user);
  }

  removeUser(): void {
    window.sessionStorage.clear();
  }

  get user() {
    return JSON.parse(sessionStorage.getItem(CURRENT_USER) as string) as User;
  }

  get regularUser() {
    const user = this.instanceOfUser(JSON.parse(sessionStorage.getItem(CURRENT_USER) as string)) ?
    (JSON.parse(sessionStorage.getItem(CURRENT_USER) as string) as User).user : JSON.parse(sessionStorage.getItem(CURRENT_USER) as string) as RegularUser;

    return user;
  }

  get role() {
    return sessionStorage.getItem(MAIN_ROLE) as Role;
  }

  get roles() {
    return JSON.parse(sessionStorage.getItem(ROLES) as string) as Role[];
  }

  private saveUser(user: User | RegularUser) {
    window.sessionStorage.removeItem(CURRENT_USER);
    window.sessionStorage.setItem(CURRENT_USER, JSON.stringify(user));
  }

  private saveRoles(user: User | RegularUser) {
    const u = this.instanceOfUser(user) ? (user as User).user : user as RegularUser;
    const roles = u.roles.map(this.getRoleNames);
    window.sessionStorage.removeItem(ROLES);
    window.sessionStorage.setItem(ROLES, JSON.stringify(roles));
  }

  private saveRole(user: User | RegularUser ){
    const u = this.instanceOfUser(user) ? (user as User).user : user as RegularUser;
    const main_role = u.roles.filter(r => r.role != Role.ROLE_USER).map(this.getRoleNames)[0];
    window.sessionStorage.removeItem(MAIN_ROLE);
    window.sessionStorage.setItem(MAIN_ROLE, main_role);
  }

  private getRoleNames(role: UserRole) {
    return role['role'];
  }

  private instanceOfUser(user: any): user is User {
    return 'walks' in user && 'dogs' in user && 'user' in user;
  }
}
