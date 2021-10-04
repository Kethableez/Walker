import { Filters } from "./filters.model";
import { WalkInfo } from "./walk-info.model";

export interface WalkWithFilters {
  walks: WalkInfo[];
  filters: Filters;
}
