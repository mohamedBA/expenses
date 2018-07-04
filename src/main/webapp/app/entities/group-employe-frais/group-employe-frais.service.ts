import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGroupEmployeFrais } from 'app/shared/model/group-employe-frais.model';

type EntityResponseType = HttpResponse<IGroupEmployeFrais>;
type EntityArrayResponseType = HttpResponse<IGroupEmployeFrais[]>;

@Injectable({ providedIn: 'root' })
export class GroupEmployeFraisService {
    private resourceUrl = SERVER_API_URL + 'api/group-employe-frais';

    constructor(private http: HttpClient) {}

    create(groupEmployeFrais: IGroupEmployeFrais): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(groupEmployeFrais);
        return this.http
            .post<IGroupEmployeFrais>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(groupEmployeFrais: IGroupEmployeFrais): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(groupEmployeFrais);
        return this.http
            .put<IGroupEmployeFrais>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IGroupEmployeFrais>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGroupEmployeFrais[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(groupEmployeFrais: IGroupEmployeFrais): IGroupEmployeFrais {
        const copy: IGroupEmployeFrais = Object.assign({}, groupEmployeFrais, {
            dtDeb:
                groupEmployeFrais.dtDeb != null && groupEmployeFrais.dtDeb.isValid() ? groupEmployeFrais.dtDeb.format(DATE_FORMAT) : null,
            dtFin:
                groupEmployeFrais.dtFin != null && groupEmployeFrais.dtFin.isValid() ? groupEmployeFrais.dtFin.format(DATE_FORMAT) : null,
            dtCre:
                groupEmployeFrais.dtCre != null && groupEmployeFrais.dtCre.isValid() ? groupEmployeFrais.dtCre.format(DATE_FORMAT) : null,
            deMod: groupEmployeFrais.deMod != null && groupEmployeFrais.deMod.isValid() ? groupEmployeFrais.deMod.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.dtDeb = res.body.dtDeb != null ? moment(res.body.dtDeb) : null;
        res.body.dtFin = res.body.dtFin != null ? moment(res.body.dtFin) : null;
        res.body.dtCre = res.body.dtCre != null ? moment(res.body.dtCre) : null;
        res.body.deMod = res.body.deMod != null ? moment(res.body.deMod) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((groupEmployeFrais: IGroupEmployeFrais) => {
            groupEmployeFrais.dtDeb = groupEmployeFrais.dtDeb != null ? moment(groupEmployeFrais.dtDeb) : null;
            groupEmployeFrais.dtFin = groupEmployeFrais.dtFin != null ? moment(groupEmployeFrais.dtFin) : null;
            groupEmployeFrais.dtCre = groupEmployeFrais.dtCre != null ? moment(groupEmployeFrais.dtCre) : null;
            groupEmployeFrais.deMod = groupEmployeFrais.deMod != null ? moment(groupEmployeFrais.deMod) : null;
        });
        return res;
    }
}
