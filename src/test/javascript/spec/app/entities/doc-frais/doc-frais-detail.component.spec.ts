/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExpensesTestModule } from '../../../test.module';
import { DOCFraisDetailComponent } from 'app/entities/doc-frais/doc-frais-detail.component';
import { DOCFrais } from 'app/shared/model/doc-frais.model';

describe('Component Tests', () => {
    describe('DOCFrais Management Detail Component', () => {
        let comp: DOCFraisDetailComponent;
        let fixture: ComponentFixture<DOCFraisDetailComponent>;
        const route = ({ data: of({ dOCFrais: new DOCFrais(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExpensesTestModule],
                declarations: [DOCFraisDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DOCFraisDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DOCFraisDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.dOCFrais).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
