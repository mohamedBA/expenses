export interface IEmployeFrais {
    id?: number;
    st?: string;
    matVeh?: string;
    marqueVeh?: string;
    km?: number;
    dernKm?: number;
    forfaitKm?: number;
    cForfait?: string;
    typeVoiture?: number;
}

export class EmployeFrais implements IEmployeFrais {
    constructor(
        public id?: number,
        public st?: string,
        public matVeh?: string,
        public marqueVeh?: string,
        public km?: number,
        public dernKm?: number,
        public forfaitKm?: number,
        public cForfait?: string,
        public typeVoiture?: number
    ) {}
}
