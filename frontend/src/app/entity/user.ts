export interface User {


    id?: number; //Nao obrigatório mandar, pois o backend gera automaticamente
    cpf: string;
    birthDate: Date;
    fullName: string;
    email: string;
    password: string;
    role: string;
}

