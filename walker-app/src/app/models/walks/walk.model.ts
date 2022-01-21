export interface Walk {
  id: string;
  dogId: string;
  ownerId: string;
  walkDateTime: Date;
  zipCode: string;
  city: string;
  districtCode: string;
  regionCode: string;
  address: string;
  walkLat: number;
  walkLon: number;
  walkDescription: string;
  booked: boolean;
  sitterReviewed: boolean;
  dogReviewed: boolean;
  sitterId: string | null;
}
