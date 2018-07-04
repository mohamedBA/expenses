/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ExpensesTestModule } from '../../../test.module';
import { DepenseDeleteDialogComponent } from 'app/entities/depense/depense-delete-dialog.component';
import { DepenseService } from 'app/entities/depense/depense.service';

describe('Component Tests', () => {
    describe('Depense Management Delete Component', () => {
        let comp: DepenseDeleteDialogComponent;
        let fixture: ComponentFixture<DepenseDeleteDialogComponent>;
        let service: DepenseService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExpensesTestModule],
                declarations: [DepenseDeleteDialogComponent]
            })
                .overrideTemplate(DepenseDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DepenseDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DepenseService);
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
