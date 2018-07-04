import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ExpensesSharedModule } from 'app/shared';
import {
    DepenseComponent,
    DepenseDetailComponent,
    DepenseUpdateComponent,
    DepenseDeletePopupComponent,
    DepenseDeleteDialogComponent,
    depenseRoute,
    depensePopupRoute
} from './';

const ENTITY_STATES = [...depenseRoute, ...depensePopupRoute];

@NgModule({
    imports: [ExpensesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DepenseComponent,
        DepenseDetailComponent,
        DepenseUpdateComponent,
        DepenseDeleteDialogComponent,
        DepenseDeletePopupComponent
    ],
    entryComponents: [DepenseComponent, DepenseUpdateComponent, DepenseDeleteDialogComponent, DepenseDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExpensesDepenseModule {}
