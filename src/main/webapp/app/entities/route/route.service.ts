import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRoute } from 'app/shared/model/route.model';

type EntityResponseType = HttpResponse<IRoute>;
type EntityArrayResponseType = HttpResponse<IRoute[]>;

@Injectable({ providedIn: 'root' })
export class RouteService {
    private resourceUrl = SERVER_API_URL + 'api/routes';

    constructor(private http: HttpClient) {}

    create(route: IRoute): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(route);
        return this.http
            .post<IRoute>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(route: IRoute): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(route);
        return this.http
            .put<IRoute>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRoute>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRoute[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(route: IRoute): IRoute {
        const copy: IRoute = Object.assign({}, route, {
            dtFdepense: route.dtFdepense != null && route.dtFdepense.isValid() ? route.dtFdepense.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.dtFdepense = res.body.dtFdepense != null ? moment(res.body.dtFdepense) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((route: IRoute) => {
            route.dtFdepense = route.dtFdepense != null ? moment(route.dtFdepense) : null;
        });
        return res;
    }
}
