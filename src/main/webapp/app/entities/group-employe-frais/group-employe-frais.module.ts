import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ExpensesSharedModule } from 'app/shared';
import {
    GroupEmployeFraisComponent,
    GroupEmployeFraisDetailComponent,
    GroupEmployeFraisUpdateComponent,
    GroupEmployeFraisDeletePopupComponent,
    GroupEmployeFraisDeleteDialogComponent,
    groupEmployeFraisRoute,
    groupEmployeFraisPopupRoute
} from './';

const ENTITY_STATES = [...groupEmployeFraisRoute, ...groupEmployeFraisPopupRoute];

@NgModule({
    imports: [ExpensesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GroupEmployeFraisComponent,
        GroupEmployeFraisDetailComponent,
        GroupEmployeFraisUpdateComponent,
        GroupEmployeFraisDeleteDialogComponent,
        GroupEmployeFraisDeletePopupComponent
    ],
    entryComponents: [
        GroupEmployeFraisComponent,
        GroupEmployeFraisUpdateComponent,
        GroupEmployeFraisDeleteDialogComponent,
        GroupEmployeFraisDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExpensesGroupEmployeFraisModule {}
