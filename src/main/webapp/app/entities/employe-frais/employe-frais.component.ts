import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEmployeFrais } from 'app/shared/model/employe-frais.model';
import { Principal } from 'app/core';
import { EmployeFraisService } from './employe-frais.service';

@Component({
    selector: 'jhi-employe-frais',
    templateUrl: './employe-frais.component.html'
})
export class EmployeFraisComponent implements OnInit, OnDestroy {
    employeFrais: IEmployeFrais[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private employeFraisService: EmployeFraisService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.employeFraisService.query().subscribe(
            (res: HttpResponse<IEmployeFrais[]>) => {
                this.employeFrais = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEmployeFrais();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEmployeFrais) {
        return item.id;
    }

    registerChangeInEmployeFrais() {
        this.eventSubscriber = this.eventManager.subscribe('employeFraisListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
