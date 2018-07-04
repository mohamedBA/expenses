import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEmployeFrais } from 'app/shared/model/employe-frais.model';
import { EmployeFraisService } from './employe-frais.service';

@Component({
    selector: 'jhi-employe-frais-delete-dialog',
    templateUrl: './employe-frais-delete-dialog.component.html'
})
export class EmployeFraisDeleteDialogComponent {
    employeFrais: IEmployeFrais;

    constructor(
        private employeFraisService: EmployeFraisService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.employeFraisService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'employeFraisListModification',
                content: 'Deleted an employeFrais'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-employe-frais-delete-popup',
    template: ''
})
export class EmployeFraisDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ employeFrais }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EmployeFraisDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.employeFrais = employeFrais;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
