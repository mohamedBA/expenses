/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ExpensesTestModule } from '../../../test.module';
import { EmployeFraisComponent } from 'app/entities/employe-frais/employe-frais.component';
import { EmployeFraisService } from 'app/entities/employe-frais/employe-frais.service';
import { EmployeFrais } from 'app/shared/model/employe-frais.model';

describe('Component Tests', () => {
    describe('EmployeFrais Management Component', () => {
        let comp: EmployeFraisComponent;
        let fixture: ComponentFixture<EmployeFraisComponent>;
        let service: EmployeFraisService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExpensesTestModule],
                declarations: [EmployeFraisComponent],
                providers: []
            })
                .overrideTemplate(EmployeFraisComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EmployeFraisComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmployeFraisService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EmployeFrais(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.employeFrais[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
