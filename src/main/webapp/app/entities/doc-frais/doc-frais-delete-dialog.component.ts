import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDOCFrais } from 'app/shared/model/doc-frais.model';
import { DOCFraisService } from './doc-frais.service';

@Component({
    selector: 'jhi-doc-frais-delete-dialog',
    templateUrl: './doc-frais-delete-dialog.component.html'
})
export class DOCFraisDeleteDialogComponent {
    dOCFrais: IDOCFrais;

    constructor(private dOCFraisService: DOCFraisService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dOCFraisService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'dOCFraisListModification',
                content: 'Deleted an dOCFrais'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-doc-frais-delete-popup',
    template: ''
})
export class DOCFraisDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dOCFrais }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DOCFraisDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.dOCFrais = dOCFrais;
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
