export enum NotificationType {
  WALK_ENROLLMENT = 'WALK_ENROLLMENT',
  WALK_DISENROLLMENT = 'WALK_DISENROLLMENT',
  DOG_REVIEW = 'DOG_REVIEW',
  SITTER_REVIEW = 'SITTER_REVIEW'
}

export interface Notification {
  id: string;
  senderId: string;
  senderName: string;
  recieverId: string;
  additionalId: string;
  type: NotificationType;
  timestamp: string;
  isRead: boolean
}
