import { Dog } from "src/app/models/dogs/dog.model";
import { WalkCard } from "src/app/models/walks/walk-card.model";
import { Walk } from "src/app/models/walks/walk.model";

export function findFirst<T>(array: T[]): T {
  return array[0];
}

export function exists<T>(obj: T): boolean {
  return !!obj;
}

export function removeById(array: any[], id: string): any[] {
  return array.filter(obj => obj.id !== id);
}

export function filterByKey(array: WalkCard[], key: string, value: string): WalkCard[] {
  return array.filter(obj => obj.walk[key as keyof Walk] === value);
}

