export interface Walk {
  id: string;
  dogId: string;
  ownerId: string;
  walkDateTime: Date;
  city: string;
  address: string;
  walkLat: number;
  walkLon: number;
  walkDescription: string;
  booked: boolean;
  sitterId: string | null;
}
