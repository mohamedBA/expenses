import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ExpensesSharedModule } from 'app/shared';
import {
    EmployeFraisComponent,
    EmployeFraisDetailComponent,
    EmployeFraisUpdateComponent,
    EmployeFraisDeletePopupComponent,
    EmployeFraisDeleteDialogComponent,
    employeFraisRoute,
    employeFraisPopupRoute
} from './';

const ENTITY_STATES = [...employeFraisRoute, ...employeFraisPopupRoute];

@NgModule({
    imports: [ExpensesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EmployeFraisComponent,
        EmployeFraisDetailComponent,
        EmployeFraisUpdateComponent,
        EmployeFraisDeleteDialogComponent,
        EmployeFraisDeletePopupComponent
    ],
    entryComponents: [
        EmployeFraisComponent,
        EmployeFraisUpdateComponent,
        EmployeFraisDeleteDialogComponent,
        EmployeFraisDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExpensesEmployeFraisModule {}
