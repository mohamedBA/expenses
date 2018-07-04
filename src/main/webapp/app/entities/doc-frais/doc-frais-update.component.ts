import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IDOCFrais } from 'app/shared/model/doc-frais.model';
import { DOCFraisService } from './doc-frais.service';

@Component({
    selector: 'jhi-doc-frais-update',
    templateUrl: './doc-frais-update.component.html'
})
export class DOCFraisUpdateComponent implements OnInit {
    private _dOCFrais: IDOCFrais;
    isSaving: boolean;
    dtSoumissionDp: any;
    dtValidationPartieleDp: any;
    dtValidationDp: any;
    dtPayementDp: any;
    dtRefusDp: any;

    constructor(private dOCFraisService: DOCFraisService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ dOCFrais }) => {
            this.dOCFrais = dOCFrais;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.dOCFrais.id !== undefined) {
            this.subscribeToSaveResponse(this.dOCFraisService.update(this.dOCFrais));
        } else {
            this.subscribeToSaveResponse(this.dOCFraisService.create(this.dOCFrais));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDOCFrais>>) {
        result.subscribe((res: HttpResponse<IDOCFrais>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get dOCFrais() {
        return this._dOCFrais;
    }

    set dOCFrais(dOCFrais: IDOCFrais) {
        this._dOCFrais = dOCFrais;
    }
}
