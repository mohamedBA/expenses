/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExpensesTestModule } from '../../../test.module';
import { EmployeFraisDetailComponent } from 'app/entities/employe-frais/employe-frais-detail.component';
import { EmployeFrais } from 'app/shared/model/employe-frais.model';

describe('Component Tests', () => {
    describe('EmployeFrais Management Detail Component', () => {
        let comp: EmployeFraisDetailComponent;
        let fixture: ComponentFixture<EmployeFraisDetailComponent>;
        const route = ({ data: of({ employeFrais: new EmployeFrais(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExpensesTestModule],
                declarations: [EmployeFraisDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EmployeFraisDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EmployeFraisDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.employeFrais).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
