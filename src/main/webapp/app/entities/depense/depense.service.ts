import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDepense } from 'app/shared/model/depense.model';

type EntityResponseType = HttpResponse<IDepense>;
type EntityArrayResponseType = HttpResponse<IDepense[]>;

@Injectable({ providedIn: 'root' })
export class DepenseService {
    private resourceUrl = SERVER_API_URL + 'api/depenses';

    constructor(private http: HttpClient) {}

    create(depense: IDepense): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(depense);
        return this.http
            .post<IDepense>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(depense: IDepense): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(depense);
        return this.http
            .put<IDepense>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDepense>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDepense[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(depense: IDepense): IDepense {
        const copy: IDepense = Object.assign({}, depense, {
            dtFdepense: depense.dtFdepense != null && depense.dtFdepense.isValid() ? depense.dtFdepense.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.dtFdepense = res.body.dtFdepense != null ? moment(res.body.dtFdepense) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((depense: IDepense) => {
            depense.dtFdepense = depense.dtFdepense != null ? moment(depense.dtFdepense) : null;
        });
        return res;
    }
}
