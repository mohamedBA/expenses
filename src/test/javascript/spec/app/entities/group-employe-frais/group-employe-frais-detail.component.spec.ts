/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExpensesTestModule } from '../../../test.module';
import { GroupEmployeFraisDetailComponent } from 'app/entities/group-employe-frais/group-employe-frais-detail.component';
import { GroupEmployeFrais } from 'app/shared/model/group-employe-frais.model';

describe('Component Tests', () => {
    describe('GroupEmployeFrais Management Detail Component', () => {
        let comp: GroupEmployeFraisDetailComponent;
        let fixture: ComponentFixture<GroupEmployeFraisDetailComponent>;
        const route = ({ data: of({ groupEmployeFrais: new GroupEmployeFrais(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExpensesTestModule],
                declarations: [GroupEmployeFraisDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GroupEmployeFraisDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GroupEmployeFraisDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.groupEmployeFrais).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
