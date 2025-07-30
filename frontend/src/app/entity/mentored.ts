import { User } from './user';

export interface Mentored extends User {
    academicSummary: string;
    interestArea: string[];
}
