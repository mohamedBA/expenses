import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { EmployeFrais } from 'app/shared/model/employe-frais.model';
import { EmployeFraisService } from './employe-frais.service';
import { EmployeFraisComponent } from './employe-frais.component';
import { EmployeFraisDetailComponent } from './employe-frais-detail.component';
import { EmployeFraisUpdateComponent } from './employe-frais-update.component';
import { EmployeFraisDeletePopupComponent } from './employe-frais-delete-dialog.component';
import { IEmployeFrais } from 'app/shared/model/employe-frais.model';

@Injectable({ providedIn: 'root' })
export class EmployeFraisResolve implements Resolve<IEmployeFrais> {
    constructor(private service: EmployeFraisService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((employeFrais: HttpResponse<EmployeFrais>) => employeFrais.body);
        }
        return Observable.of(new EmployeFrais());
    }
}

export const employeFraisRoute: Routes = [
    {
        path: 'employe-frais',
        component: EmployeFraisComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EmployeFrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employe-frais/:id/view',
        component: EmployeFraisDetailComponent,
        resolve: {
            employeFrais: EmployeFraisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EmployeFrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employe-frais/new',
        component: EmployeFraisUpdateComponent,
        resolve: {
            employeFrais: EmployeFraisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EmployeFrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employe-frais/:id/edit',
        component: EmployeFraisUpdateComponent,
        resolve: {
            employeFrais: EmployeFraisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EmployeFrais'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const employeFraisPopupRoute: Routes = [
    {
        path: 'employe-frais/:id/delete',
        component: EmployeFraisDeletePopupComponent,
        resolve: {
            employeFrais: EmployeFraisResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EmployeFrais'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
