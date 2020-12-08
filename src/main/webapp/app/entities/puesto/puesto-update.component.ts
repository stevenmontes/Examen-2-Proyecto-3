import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPuesto, Puesto } from 'app/shared/model/puesto.model';
import { PuestoService } from './puesto.service';

@Component({
  selector: 'jhi-puesto-update',
  templateUrl: './puesto-update.component.html',
})
export class PuestoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [],
    estado: [],
  });

  constructor(protected puestoService: PuestoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ puesto }) => {
      this.updateForm(puesto);
    });
  }

  updateForm(puesto: IPuesto): void {
    this.editForm.patchValue({
      id: puesto.id,
      nombre: puesto.nombre,
      estado: puesto.estado,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const puesto = this.createFromForm();
    if (puesto.id !== undefined) {
      this.subscribeToSaveResponse(this.puestoService.update(puesto));
    } else {
      this.subscribeToSaveResponse(this.puestoService.create(puesto));
    }
  }

  private createFromForm(): IPuesto {
    return {
      ...new Puesto(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      estado: this.editForm.get(['estado'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPuesto>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
