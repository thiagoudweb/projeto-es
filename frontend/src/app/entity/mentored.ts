import { User } from './user';

export interface Mentored extends User {
    cpf: string;
    birthDate: string;
    course: string;
    academicSummary: string;
}
