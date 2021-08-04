import { Dog } from "../dogs/dog.model";
import { Walk } from "../walks/walk.model";
import { RegularUser } from "./regular-user.model";

export interface User {
  user: RegularUser;
  dogs: Dog[];
  walks: Walk[];
}
