import { UserService } from 'src/app/core/services/models/user.service';
import { Injectable } from '@angular/core';
import { RegularUser } from 'src/app/models/users/regular-user.model';
import { User } from 'src/app/models/users/user.model';
import { Role } from 'src/app/models/enums/role.model';

const CURRENT_USER = 'current_user';
const MAIN_ROLE = 'main_role';
const ROLES = 'user_roles';
const REGION_CODE ='region_code';
const DISTRICT_CODE = 'district_code';

@Injectable({
  providedIn: 'root',
})
export class CurrentUserStoreService {
  constructor ( ) { };

  save(user: any) {
    this.saveUser(user);
    this.saveRoles(user);
    this.saveRole(user);
    this.saveAddressCodes(user);
  }

  get districtCode() {
    const districtCode = JSON.parse(sessionStorage.getItem(DISTRICT_CODE) as string) as string;
    return districtCode;
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
    const user = JSON.parse(sessionStorage.getItem(CURRENT_USER) as string) as RegularUser;
    return user;
  }

  get role() {
    return sessionStorage.getItem(MAIN_ROLE) as Role;
  }

  get roles() {
    return JSON.parse(sessionStorage.getItem(ROLES) as string) as Role[];
  }

  private saveUser(user: RegularUser) {
    window.sessionStorage.removeItem(CURRENT_USER);
    window.sessionStorage.setItem(CURRENT_USER, JSON.stringify(user));
  }

  private saveRoles(user: RegularUser) {
    const roles = user.roles;
    window.sessionStorage.removeItem(ROLES);
    window.sessionStorage.setItem(ROLES, JSON.stringify(roles));
  }

  private saveRole(user: RegularUser ){
    const main_role = user.roles.filter(role => role != Role.ROLE_USER)[0];
    // const main_role = u.roles.filter(r => r.role != Role.ROLE_USER).map(this.getRoleNames)[0];
    window.sessionStorage.removeItem(MAIN_ROLE);
    window.sessionStorage.setItem(MAIN_ROLE, main_role);
  }

  private saveAddressCodes(user: RegularUser) {
    const {districtCode, regionCode} = user;
    window.sessionStorage.removeItem(REGION_CODE);
    window.sessionStorage.removeItem(DISTRICT_CODE);
    window.sessionStorage.setItem(REGION_CODE, JSON.stringify(regionCode));
    window.sessionStorage.setItem(DISTRICT_CODE, JSON.stringify(districtCode));
  }

  // private getRoleNames(role: UserRole) {
  //   return role['role'];
  // }

  private instanceOfUser(user: any): user is User {
    return 'walks' in user && 'dogs' in user && 'user' in user;
  }
}
