import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IEmployeFrais } from 'app/shared/model/employe-frais.model';
import { EmployeFraisService } from './employe-frais.service';

@Component({
    selector: 'jhi-employe-frais-update',
    templateUrl: './employe-frais-update.component.html'
})
export class EmployeFraisUpdateComponent implements OnInit {
    private _employeFrais: IEmployeFrais;
    isSaving: boolean;

    constructor(private employeFraisService: EmployeFraisService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ employeFrais }) => {
            this.employeFrais = employeFrais;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.employeFrais.id !== undefined) {
            this.subscribeToSaveResponse(this.employeFraisService.update(this.employeFrais));
        } else {
            this.subscribeToSaveResponse(this.employeFraisService.create(this.employeFrais));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEmployeFrais>>) {
        result.subscribe((res: HttpResponse<IEmployeFrais>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get employeFrais() {
        return this._employeFrais;
    }

    set employeFrais(employeFrais: IEmployeFrais) {
        this._employeFrais = employeFrais;
    }
}
