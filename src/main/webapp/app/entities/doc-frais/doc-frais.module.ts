import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ExpensesSharedModule } from 'app/shared';
import {
    DOCFraisComponent,
    DOCFraisDetailComponent,
    DOCFraisUpdateComponent,
    DOCFraisDeletePopupComponent,
    DOCFraisDeleteDialogComponent,
    dOCFraisRoute,
    dOCFraisPopupRoute
} from './';

const ENTITY_STATES = [...dOCFraisRoute, ...dOCFraisPopupRoute];

@NgModule({
    imports: [ExpensesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DOCFraisComponent,
        DOCFraisDetailComponent,
        DOCFraisUpdateComponent,
        DOCFraisDeleteDialogComponent,
        DOCFraisDeletePopupComponent
    ],
    entryComponents: [DOCFraisComponent, DOCFraisUpdateComponent, DOCFraisDeleteDialogComponent, DOCFraisDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExpensesDOCFraisModule {}
