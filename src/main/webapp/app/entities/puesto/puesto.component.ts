import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPuesto } from 'app/shared/model/puesto.model';
import { PuestoService } from './puesto.service';
import { PuestoDeleteDialogComponent } from './puesto-delete-dialog.component';

@Component({
  selector: 'jhi-puesto',
  templateUrl: './puesto.component.html',
})
export class PuestoComponent implements OnInit, OnDestroy {
  puestos?: IPuesto[];
  eventSubscriber?: Subscription;

  constructor(protected puestoService: PuestoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.puestoService.query().subscribe((res: HttpResponse<IPuesto[]>) => (this.puestos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPuestos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPuesto): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPuestos(): void {
    this.eventSubscriber = this.eventManager.subscribe('puestoListModification', () => this.loadAll());
  }

  delete(puesto: IPuesto): void {
    const modalRef = this.modalService.open(PuestoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.puesto = puesto;
  }
}
