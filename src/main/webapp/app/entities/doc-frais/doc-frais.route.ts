import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { DOCFrais } from 'app/shared/model/doc-frais.model';
import { DOCFraisService } from './doc-frais.service';
import { DOCFraisComponent } from './doc-frais.component';
import { DOCFraisDetailComponent } from './doc-frais-detail.component';
import { DOCFraisUpdateComponent } from './doc-frais-update.component';
import { DOCFraisDeletePopupComponent } from './doc-frais-delete-dialog.component';
import { IDOCFrais } from 'app/shared/model/doc-frais.model';

@Injectable({ providedIn: 'root' })
export class DOCFraisResolve implements Resolve<IDOCFrais> {
    constructor(private service: DOCFraisService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((dOCFrais: HttpResponse<DOCFrais>) => dOCFrais.body);
        }
        return Observable.of(new DOCFrais());
    }
}

export const dOCFraisRoute: Routes = [
    {
        path: 'doc-frais',
        component: DOCFraisComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'DOCFrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'doc-frais/:id/view',
        component: DOCFraisDetailComponent,
        resolve: {
            dOCFrais: DOCFraisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DOCFrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'doc-frais/new',
        component: DOCFraisUpdateComponent,
        resolve: {
            dOCFrais: DOCFraisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DOCFrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'doc-frais/:id/edit',
        component: DOCFraisUpdateComponent,
        resolve: {
            dOCFrais: DOCFraisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DOCFrais'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dOCFraisPopupRoute: Routes = [
    {
        path: 'doc-frais/:id/delete',
        component: DOCFraisDeletePopupComponent,
        resolve: {
            dOCFrais: DOCFraisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DOCFrais'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
