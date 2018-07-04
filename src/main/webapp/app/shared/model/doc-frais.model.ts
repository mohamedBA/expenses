import { Moment } from 'moment';

export interface IDOCFrais {
    id?: number;
    tDocFrais?: number;
    totalFrais?: number;
    dtSoumission?: Moment;
    dtValidationPartiele?: Moment;
    dtValidation?: Moment;
    dtPayement?: Moment;
    dtRefus?: Moment;
    motifRefus?: string;
    totalFroute?: number;
    totalDepense?: number;
    tUserCre?: number;
    tUserModif?: number;
    tPUser?: number;
    totalNbreKm?: number;
}

export class DOCFrais implements IDOCFrais {
    constructor(
        public id?: number,
        public tDocFrais?: number,
        public totalFrais?: number,
        public dtSoumission?: Moment,
        public dtValidationPartiele?: Moment,
        public dtValidation?: Moment,
        public dtPayement?: Moment,
        public dtRefus?: Moment,
        public motifRefus?: string,
        public totalFroute?: number,
        public totalDepense?: number,
        public tUserCre?: number,
        public tUserModif?: number,
        public tPUser?: number,
        public totalNbreKm?: number
    ) {}
}
