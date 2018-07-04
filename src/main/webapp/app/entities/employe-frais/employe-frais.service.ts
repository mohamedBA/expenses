import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEmployeFrais } from 'app/shared/model/employe-frais.model';

type EntityResponseType = HttpResponse<IEmployeFrais>;
type EntityArrayResponseType = HttpResponse<IEmployeFrais[]>;

@Injectable({ providedIn: 'root' })
export class EmployeFraisService {
    private resourceUrl = SERVER_API_URL + 'api/employe-frais';

    constructor(private http: HttpClient) {}

    create(employeFrais: IEmployeFrais): Observable<EntityResponseType> {
        return this.http.post<IEmployeFrais>(this.resourceUrl, employeFrais, { observe: 'response' });
    }

    update(employeFrais: IEmployeFrais): Observable<EntityResponseType> {
        return this.http.put<IEmployeFrais>(this.resourceUrl, employeFrais, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEmployeFrais>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEmployeFrais[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
