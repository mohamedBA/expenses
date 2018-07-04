import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ExpensesDOCFraisModule } from './doc-frais/doc-frais.module';
import { ExpensesDepenseModule } from './depense/depense.module';
import { ExpensesEmployeeModule } from './employee/employee.module';
import { ExpensesEmployeFraisModule } from './employe-frais/employe-frais.module';
import { ExpensesGroupEmployeFraisModule } from './group-employe-frais/group-employe-frais.module';
import { ExpensesRouteModule } from './route/route.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        ExpensesDOCFraisModule,
        ExpensesDepenseModule,
        ExpensesEmployeeModule,
        ExpensesEmployeFraisModule,
        ExpensesGroupEmployeFraisModule,
        ExpensesRouteModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExpensesEntityModule {}
