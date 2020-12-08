import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPuesto } from 'app/shared/model/puesto.model';
import { PuestoService } from './puesto.service';

@Component({
  templateUrl: './puesto-delete-dialog.component.html',
})
export class PuestoDeleteDialogComponent {
  puesto?: IPuesto;

  constructor(protected puestoService: PuestoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.puestoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('puestoListModification');
      this.activeModal.close();
    });
  }
}
