import { Moment } from 'moment';

export interface IEmployee {
    id?: number;
    tEmploye?: number;
    tSocab?: number;
    matEmpl?: string;
    idCarte?: number;
    nom?: string;
    prenom?: string;
    nCin?: number;
    photoContentType?: string;
    photo?: any;
    dateDelivCin?: Moment;
}

export class Employee implements IEmployee {
    constructor(
        public id?: number,
        public tEmploye?: number,
        public tSocab?: number,
        public matEmpl?: string,
        public idCarte?: number,
        public nom?: string,
        public prenom?: string,
        public nCin?: number,
        public photoContentType?: string,
        public photo?: any,
        public dateDelivCin?: Moment
    ) {}
}
