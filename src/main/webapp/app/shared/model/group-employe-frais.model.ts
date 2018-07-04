import { Moment } from 'moment';

export interface IGroupEmployeFrais {
    id?: number;
    plafond?: number;
    dtDeb?: Moment;
    dtFin?: Moment;
    tUserCre?: number;
    tUserMod?: number;
    dtCre?: Moment;
    deMod?: Moment;
}

export class GroupEmployeFrais implements IGroupEmployeFrais {
    constructor(
        public id?: number,
        public plafond?: number,
        public dtDeb?: Moment,
        public dtFin?: Moment,
        public tUserCre?: number,
        public tUserMod?: number,
        public dtCre?: Moment,
        public deMod?: Moment
    ) {}
}
