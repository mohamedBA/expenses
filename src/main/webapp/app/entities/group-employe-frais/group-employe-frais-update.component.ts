import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IGroupEmployeFrais } from 'app/shared/model/group-employe-frais.model';
import { GroupEmployeFraisService } from './group-employe-frais.service';

@Component({
    selector: 'jhi-group-employe-frais-update',
    templateUrl: './group-employe-frais-update.component.html'
})
export class GroupEmployeFraisUpdateComponent implements OnInit {
    private _groupEmployeFrais: IGroupEmployeFrais;
    isSaving: boolean;
    dtDebDp: any;
    dtFinDp: any;
    dtCreDp: any;
    deModDp: any;

    constructor(private groupEmployeFraisService: GroupEmployeFraisService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ groupEmployeFrais }) => {
            this.groupEmployeFrais = groupEmployeFrais;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.groupEmployeFrais.id !== undefined) {
            this.subscribeToSaveResponse(this.groupEmployeFraisService.update(this.groupEmployeFrais));
        } else {
            this.subscribeToSaveResponse(this.groupEmployeFraisService.create(this.groupEmployeFrais));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGroupEmployeFrais>>) {
        result.subscribe((res: HttpResponse<IGroupEmployeFrais>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get groupEmployeFrais() {
        return this._groupEmployeFrais;
    }

    set groupEmployeFrais(groupEmployeFrais: IGroupEmployeFrais) {
        this._groupEmployeFrais = groupEmployeFrais;
    }
}
