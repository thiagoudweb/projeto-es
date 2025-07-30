import { User } from './user';

export interface Mentor extends User {
    professionalSummary: string;
    affiliationType: string;
    specializations: string[];
    interestArea: string[];
}
