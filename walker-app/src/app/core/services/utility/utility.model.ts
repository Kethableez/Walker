export function findFirst<T>(array: T[]): T {
  return array[0];
}

export function exists<T>(obj: T): boolean {
  return !!obj;
}

export function removeById(array: any[], id: string): any[] {
  return array.filter(obj => obj.id !== id);
}
