import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { GroupEmployeFrais } from 'app/shared/model/group-employe-frais.model';
import { GroupEmployeFraisService } from './group-employe-frais.service';
import { GroupEmployeFraisComponent } from './group-employe-frais.component';
import { GroupEmployeFraisDetailComponent } from './group-employe-frais-detail.component';
import { GroupEmployeFraisUpdateComponent } from './group-employe-frais-update.component';
import { GroupEmployeFraisDeletePopupComponent } from './group-employe-frais-delete-dialog.component';
import { IGroupEmployeFrais } from 'app/shared/model/group-employe-frais.model';

@Injectable({ providedIn: 'root' })
export class GroupEmployeFraisResolve implements Resolve<IGroupEmployeFrais> {
    constructor(private service: GroupEmployeFraisService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((groupEmployeFrais: HttpResponse<GroupEmployeFrais>) => groupEmployeFrais.body);
        }
        return Observable.of(new GroupEmployeFrais());
    }
}

export const groupEmployeFraisRoute: Routes = [
    {
        path: 'group-employe-frais',
        component: GroupEmployeFraisComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroupEmployeFrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'group-employe-frais/:id/view',
        component: GroupEmployeFraisDetailComponent,
        resolve: {
            groupEmployeFrais: GroupEmployeFraisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroupEmployeFrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'group-employe-frais/new',
        component: GroupEmployeFraisUpdateComponent,
        resolve: {
            groupEmployeFrais: GroupEmployeFraisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroupEmployeFrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'group-employe-frais/:id/edit',
        component: GroupEmployeFraisUpdateComponent,
        resolve: {
            groupEmployeFrais: GroupEmployeFraisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroupEmployeFrais'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const groupEmployeFraisPopupRoute: Routes = [
    {
        path: 'group-employe-frais/:id/delete',
        component: GroupEmployeFraisDeletePopupComponent,
        resolve: {
            groupEmployeFrais: GroupEmployeFraisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GroupEmployeFrais'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
