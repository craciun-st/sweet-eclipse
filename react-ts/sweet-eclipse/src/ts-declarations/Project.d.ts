import {Image} from "./Image";
import {Tag} from "./Tag";

export type ProjectStatus =
    "ACTIVE" |
    "CANCELLED" |
    "COMPLETED" |
    "MISSING_INFO";

export interface Project {
    id: number,
    status: ProjectStatus,
    title: string,
    description?: string,
    currentFunds: number,
    fundingGoal: number,
    nrDonors: number,
    images: Image[],
    tags?: Tag[]
}