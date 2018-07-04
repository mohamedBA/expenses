import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDOCFrais } from 'app/shared/model/doc-frais.model';

@Component({
    selector: 'jhi-doc-frais-detail',
    templateUrl: './doc-frais-detail.component.html'
})
export class DOCFraisDetailComponent implements OnInit {
    dOCFrais: IDOCFrais;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dOCFrais }) => {
            this.dOCFrais = dOCFrais;
        });
    }

    previousState() {
        window.history.back();
    }
}
