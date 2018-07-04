/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ExpensesTestModule } from '../../../test.module';
import { GroupEmployeFraisComponent } from 'app/entities/group-employe-frais/group-employe-frais.component';
import { GroupEmployeFraisService } from 'app/entities/group-employe-frais/group-employe-frais.service';
import { GroupEmployeFrais } from 'app/shared/model/group-employe-frais.model';

describe('Component Tests', () => {
    describe('GroupEmployeFrais Management Component', () => {
        let comp: GroupEmployeFraisComponent;
        let fixture: ComponentFixture<GroupEmployeFraisComponent>;
        let service: GroupEmployeFraisService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExpensesTestModule],
                declarations: [GroupEmployeFraisComponent],
                providers: []
            })
                .overrideTemplate(GroupEmployeFraisComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GroupEmployeFraisComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroupEmployeFraisService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new GroupEmployeFrais(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.groupEmployeFrais[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
