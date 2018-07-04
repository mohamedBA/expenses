import { Moment } from 'moment';

export interface IRoute {
    id?: number;
    tFDepense?: number;
    tDocFrais?: number;
    cTypeDepense?: string;
    dtFdepense?: Moment;
    remarque?: string;
    montant?: number;
    mtAccp?: number;
    mtDepass?: number;
    plafond?: number;
}

export class Route implements IRoute {
    constructor(
        public id?: number,
        public tFDepense?: number,
        public tDocFrais?: number,
        public cTypeDepense?: string,
        public dtFdepense?: Moment,
        public remarque?: string,
        public montant?: number,
        public mtAccp?: number,
        public mtDepass?: number,
        public plafond?: number
    ) {}
}
