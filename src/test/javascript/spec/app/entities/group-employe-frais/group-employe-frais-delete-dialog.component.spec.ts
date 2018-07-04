/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ExpensesTestModule } from '../../../test.module';
import { GroupEmployeFraisDeleteDialogComponent } from 'app/entities/group-employe-frais/group-employe-frais-delete-dialog.component';
import { GroupEmployeFraisService } from 'app/entities/group-employe-frais/group-employe-frais.service';

describe('Component Tests', () => {
    describe('GroupEmployeFrais Management Delete Component', () => {
        let comp: GroupEmployeFraisDeleteDialogComponent;
        let fixture: ComponentFixture<GroupEmployeFraisDeleteDialogComponent>;
        let service: GroupEmployeFraisService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExpensesTestModule],
                declarations: [GroupEmployeFraisDeleteDialogComponent]
            })
                .overrideTemplate(GroupEmployeFraisDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GroupEmployeFraisDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroupEmployeFraisService);
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
