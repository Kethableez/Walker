import { Filters } from "./filters.model";
import { WalkCard } from "./walk-card.model";

export interface WalkWithFilters {
  walks: WalkCard[];
  filters: Filters;
}
