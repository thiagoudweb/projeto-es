

export interface responseMentor {
    id: number;
    fullName: string;
    cpf: string;
    course: string;
    bithDate: Date;
    professionalSummary: string[];
    user: {
        id: number;
        username: string;
        role: string;
    };
}

