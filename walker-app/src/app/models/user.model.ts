export interface User {
  id: number;
  username: string;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  birthdate: Date;
  age: number;
  avatar: string;
  gender: string;
  roles: string[];
  isActive: boolean;
  isSubscribed: boolean;
}
