/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ExpensesTestModule } from '../../../test.module';
import { DOCFraisUpdateComponent } from 'app/entities/doc-frais/doc-frais-update.component';
import { DOCFraisService } from 'app/entities/doc-frais/doc-frais.service';
import { DOCFrais } from 'app/shared/model/doc-frais.model';

describe('Component Tests', () => {
    describe('DOCFrais Management Update Component', () => {
        let comp: DOCFraisUpdateComponent;
        let fixture: ComponentFixture<DOCFraisUpdateComponent>;
        let service: DOCFraisService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExpensesTestModule],
                declarations: [DOCFraisUpdateComponent]
            })
                .overrideTemplate(DOCFraisUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DOCFraisUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DOCFraisService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DOCFrais(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.dOCFrais = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DOCFrais();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.dOCFrais = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
