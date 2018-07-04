import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IGroupEmployeFrais } from 'app/shared/model/group-employe-frais.model';
import { Principal } from 'app/core';
import { GroupEmployeFraisService } from './group-employe-frais.service';

@Component({
    selector: 'jhi-group-employe-frais',
    templateUrl: './group-employe-frais.component.html'
})
export class GroupEmployeFraisComponent implements OnInit, OnDestroy {
    groupEmployeFrais: IGroupEmployeFrais[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private groupEmployeFraisService: GroupEmployeFraisService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.groupEmployeFraisService.query().subscribe(
            (res: HttpResponse<IGroupEmployeFrais[]>) => {
                this.groupEmployeFrais = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInGroupEmployeFrais();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IGroupEmployeFrais) {
        return item.id;
    }

    registerChangeInGroupEmployeFrais() {
        this.eventSubscriber = this.eventManager.subscribe('groupEmployeFraisListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
