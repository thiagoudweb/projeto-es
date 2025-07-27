import { User } from './user';

export interface Mentor extends User {
    cpf: string;
    birthDate: string; 
    course: string;
    professionalSummary: string;
    affiliationType: string;
    specializations: string[];
}
