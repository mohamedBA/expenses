import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { Depense } from 'app/shared/model/depense.model';
import { DepenseService } from './depense.service';
import { DepenseComponent } from './depense.component';
import { DepenseDetailComponent } from './depense-detail.component';
import { DepenseUpdateComponent } from './depense-update.component';
import { DepenseDeletePopupComponent } from './depense-delete-dialog.component';
import { IDepense } from 'app/shared/model/depense.model';

@Injectable({ providedIn: 'root' })
export class DepenseResolve implements Resolve<IDepense> {
    constructor(private service: DepenseService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((depense: HttpResponse<Depense>) => depense.body);
        }
        return Observable.of(new Depense());
    }
}

export const depenseRoute: Routes = [
    {
        path: 'depense',
        component: DepenseComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Depenses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'depense/:id/view',
        component: DepenseDetailComponent,
        resolve: {
            depense: DepenseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Depenses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'depense/new',
        component: DepenseUpdateComponent,
        resolve: {
            depense: DepenseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Depenses'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'depense/:id/edit',
        component: DepenseUpdateComponent,
        resolve: {
            depense: DepenseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Depenses'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const depensePopupRoute: Routes = [
    {
        path: 'depense/:id/delete',
        component: DepenseDeletePopupComponent,
        resolve: {
            depense: DepenseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Depenses'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
