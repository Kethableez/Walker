export enum ActivityType {
  REGISTER,
  DOG_CREATE,
  DOG_REMOVE,
  WALK_CREATE,
  WALK_REMOVE,
  WALK_ENROLLMENT,
  WALK_DISENROLLMENT,
  DOG_REVIEW,
  SITTER_REVIEW,
  REPORT
}

export interface Activity {
  id: string;
  timestamp: string;
  type: ActivityType;
  userId: string;
}
