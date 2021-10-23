import { ReportStatus } from "../core/services/models/admin.service";

export interface Report {
  id: string;
  reportBody: string;
  reportDateTime: string;
  status: ReportStatus;
  reporterId: string;
}
