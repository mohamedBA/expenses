import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGroupEmployeFrais } from 'app/shared/model/group-employe-frais.model';

@Component({
    selector: 'jhi-group-employe-frais-detail',
    templateUrl: './group-employe-frais-detail.component.html'
})
export class GroupEmployeFraisDetailComponent implements OnInit {
    groupEmployeFrais: IGroupEmployeFrais;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ groupEmployeFrais }) => {
            this.groupEmployeFrais = groupEmployeFrais;
        });
    }

    previousState() {
        window.history.back();
    }
}
