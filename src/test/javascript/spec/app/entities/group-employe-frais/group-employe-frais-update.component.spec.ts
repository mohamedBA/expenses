/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ExpensesTestModule } from '../../../test.module';
import { GroupEmployeFraisUpdateComponent } from 'app/entities/group-employe-frais/group-employe-frais-update.component';
import { GroupEmployeFraisService } from 'app/entities/group-employe-frais/group-employe-frais.service';
import { GroupEmployeFrais } from 'app/shared/model/group-employe-frais.model';

describe('Component Tests', () => {
    describe('GroupEmployeFrais Management Update Component', () => {
        let comp: GroupEmployeFraisUpdateComponent;
        let fixture: ComponentFixture<GroupEmployeFraisUpdateComponent>;
        let service: GroupEmployeFraisService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExpensesTestModule],
                declarations: [GroupEmployeFraisUpdateComponent]
            })
                .overrideTemplate(GroupEmployeFraisUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GroupEmployeFraisUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroupEmployeFraisService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new GroupEmployeFrais(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.groupEmployeFrais = entity;
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
                    const entity = new GroupEmployeFrais();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.groupEmployeFrais = entity;
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
