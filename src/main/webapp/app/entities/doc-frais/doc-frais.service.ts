import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDOCFrais } from 'app/shared/model/doc-frais.model';

type EntityResponseType = HttpResponse<IDOCFrais>;
type EntityArrayResponseType = HttpResponse<IDOCFrais[]>;

@Injectable({ providedIn: 'root' })
export class DOCFraisService {
    private resourceUrl = SERVER_API_URL + 'api/doc-frais';

    constructor(private http: HttpClient) {}

    create(dOCFrais: IDOCFrais): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(dOCFrais);
        return this.http
            .post<IDOCFrais>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(dOCFrais: IDOCFrais): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(dOCFrais);
        return this.http
            .put<IDOCFrais>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDOCFrais>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDOCFrais[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(dOCFrais: IDOCFrais): IDOCFrais {
        const copy: IDOCFrais = Object.assign({}, dOCFrais, {
            dtSoumission:
                dOCFrais.dtSoumission != null && dOCFrais.dtSoumission.isValid() ? dOCFrais.dtSoumission.format(DATE_FORMAT) : null,
            dtValidationPartiele:
                dOCFrais.dtValidationPartiele != null && dOCFrais.dtValidationPartiele.isValid()
                    ? dOCFrais.dtValidationPartiele.format(DATE_FORMAT)
                    : null,
            dtValidation:
                dOCFrais.dtValidation != null && dOCFrais.dtValidation.isValid() ? dOCFrais.dtValidation.format(DATE_FORMAT) : null,
            dtPayement: dOCFrais.dtPayement != null && dOCFrais.dtPayement.isValid() ? dOCFrais.dtPayement.format(DATE_FORMAT) : null,
            dtRefus: dOCFrais.dtRefus != null && dOCFrais.dtRefus.isValid() ? dOCFrais.dtRefus.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.dtSoumission = res.body.dtSoumission != null ? moment(res.body.dtSoumission) : null;
        res.body.dtValidationPartiele = res.body.dtValidationPartiele != null ? moment(res.body.dtValidationPartiele) : null;
        res.body.dtValidation = res.body.dtValidation != null ? moment(res.body.dtValidation) : null;
        res.body.dtPayement = res.body.dtPayement != null ? moment(res.body.dtPayement) : null;
        res.body.dtRefus = res.body.dtRefus != null ? moment(res.body.dtRefus) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((dOCFrais: IDOCFrais) => {
            dOCFrais.dtSoumission = dOCFrais.dtSoumission != null ? moment(dOCFrais.dtSoumission) : null;
            dOCFrais.dtValidationPartiele = dOCFrais.dtValidationPartiele != null ? moment(dOCFrais.dtValidationPartiele) : null;
            dOCFrais.dtValidation = dOCFrais.dtValidation != null ? moment(dOCFrais.dtValidation) : null;
            dOCFrais.dtPayement = dOCFrais.dtPayement != null ? moment(dOCFrais.dtPayement) : null;
            dOCFrais.dtRefus = dOCFrais.dtRefus != null ? moment(dOCFrais.dtRefus) : null;
        });
        return res;
    }
}
