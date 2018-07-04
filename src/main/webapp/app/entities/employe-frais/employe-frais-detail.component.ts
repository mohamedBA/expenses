import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEmployeFrais } from 'app/shared/model/employe-frais.model';

@Component({
    selector: 'jhi-employe-frais-detail',
    templateUrl: './employe-frais-detail.component.html'
})
export class EmployeFraisDetailComponent implements OnInit {
    employeFrais: IEmployeFrais;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ employeFrais }) => {
            this.employeFrais = employeFrais;
        });
    }

    previousState() {
        window.history.back();
    }
}
