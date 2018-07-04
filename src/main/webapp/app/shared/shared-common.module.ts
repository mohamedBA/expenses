import { NgModule } from '@angular/core';

import { ExpensesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [ExpensesSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [ExpensesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class ExpensesSharedCommonModule {}
