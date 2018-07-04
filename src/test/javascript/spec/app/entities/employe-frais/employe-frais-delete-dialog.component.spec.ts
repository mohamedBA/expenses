/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ExpensesTestModule } from '../../../test.module';
import { EmployeFraisDeleteDialogComponent } from 'app/entities/employe-frais/employe-frais-delete-dialog.component';
import { EmployeFraisService } from 'app/entities/employe-frais/employe-frais.service';

describe('Component Tests', () => {
    describe('EmployeFrais Management Delete Component', () => {
        let comp: EmployeFraisDeleteDialogComponent;
        let fixture: ComponentFixture<EmployeFraisDeleteDialogComponent>;
        let service: EmployeFraisService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExpensesTestModule],
                declarations: [EmployeFraisDeleteDialogComponent]
            })
                .overrideTemplate(EmployeFraisDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EmployeFraisDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmployeFraisService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
