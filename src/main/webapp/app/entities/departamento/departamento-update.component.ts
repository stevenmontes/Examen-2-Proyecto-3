import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDepartamento, Departamento } from 'app/shared/model/departamento.model';
import { DepartamentoService } from './departamento.service';
import { IEmpleado } from 'app/shared/model/empleado.model';
import { EmpleadoService } from 'app/entities/empleado/empleado.service';

@Component({
  selector: 'jhi-departamento-update',
  templateUrl: './departamento-update.component.html',
})
export class DepartamentoUpdateComponent implements OnInit {
  isSaving = false;
  empleados: IEmpleado[] = [];

  editForm = this.fb.group({
    id: [],
    nombre: [],
    descripcion: [],
    estado: [],
    empleados: [],
  });

  constructor(
    protected departamentoService: DepartamentoService,
    protected empleadoService: EmpleadoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ departamento }) => {
      this.updateForm(departamento);

      this.empleadoService.query().subscribe((res: HttpResponse<IEmpleado[]>) => (this.empleados = res.body || []));
    });
  }

  updateForm(departamento: IDepartamento): void {
    this.editForm.patchValue({
      id: departamento.id,
      nombre: departamento.nombre,
      descripcion: departamento.descripcion,
      estado: departamento.estado,
      empleados: departamento.empleados,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const departamento = this.createFromForm();
    if (departamento.id !== undefined) {
      this.subscribeToSaveResponse(this.departamentoService.update(departamento));
    } else {
      this.subscribeToSaveResponse(this.departamentoService.create(departamento));
    }
  }

  private createFromForm(): IDepartamento {
    return {
      ...new Departamento(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      descripcion: this.editForm.get(['descripcion'])!.value,
      estado: this.editForm.get(['estado'])!.value,
      empleados: this.editForm.get(['empleados'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDepartamento>>): void {
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

  trackById(index: number, item: IEmpleado): any {
    return item.id;
  }

  getSelected(selectedVals: IEmpleado[], option: IEmpleado): IEmpleado {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
