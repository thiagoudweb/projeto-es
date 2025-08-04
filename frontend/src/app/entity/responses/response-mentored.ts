

export interface responseMentored {
    id: number;
    fullName: string;
    cpf: string;
    course: string;
    bithDate: Date;
    academicSummary: string[];
    user: {
        id: number;
        username: string;
        role: string;
    };
}

