import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IDepense } from 'app/shared/model/depense.model';
import { DepenseService } from './depense.service';

@Component({
    selector: 'jhi-depense-update',
    templateUrl: './depense-update.component.html'
})
export class DepenseUpdateComponent implements OnInit {
    private _depense: IDepense;
    isSaving: boolean;
    dtFdepenseDp: any;

    constructor(private depenseService: DepenseService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ depense }) => {
            this.depense = depense;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.depense.id !== undefined) {
            this.subscribeToSaveResponse(this.depenseService.update(this.depense));
        } else {
            this.subscribeToSaveResponse(this.depenseService.create(this.depense));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDepense>>) {
        result.subscribe((res: HttpResponse<IDepense>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get depense() {
        return this._depense;
    }

    set depense(depense: IDepense) {
        this._depense = depense;
    }
}
