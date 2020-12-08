import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEmpleado, Empleado } from 'app/shared/model/empleado.model';
import { EmpleadoService } from './empleado.service';

@Component({
  selector: 'jhi-empleado-update',
  templateUrl: './empleado-update.component.html',
})
export class EmpleadoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [],
    primerApellido: [],
    segundoApellido: [],
    sexo: [],
    fechaNacimiento: [],
    fechaIngreso: [],
    salario: [],
    puesto: [],
    estado: [],
  });

  constructor(protected empleadoService: EmpleadoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ empleado }) => {
      if (!empleado.id) {
        const today = moment().startOf('day');
        empleado.fechaNacimiento = today;
        empleado.fechaIngreso = today;
      }

      this.updateForm(empleado);
    });
  }

  updateForm(empleado: IEmpleado): void {
    this.editForm.patchValue({
      id: empleado.id,
      nombre: empleado.nombre,
      primerApellido: empleado.primerApellido,
      segundoApellido: empleado.segundoApellido,
      sexo: empleado.sexo,
      fechaNacimiento: empleado.fechaNacimiento ? empleado.fechaNacimiento.format(DATE_TIME_FORMAT) : null,
      fechaIngreso: empleado.fechaIngreso ? empleado.fechaIngreso.format(DATE_TIME_FORMAT) : null,
      salario: empleado.salario,
      puesto: empleado.puesto,
      estado: empleado.estado,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const empleado = this.createFromForm();
    if (empleado.id !== undefined) {
      this.subscribeToSaveResponse(this.empleadoService.update(empleado));
    } else {
      this.subscribeToSaveResponse(this.empleadoService.create(empleado));
    }
  }

  private createFromForm(): IEmpleado {
    return {
      ...new Empleado(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      primerApellido: this.editForm.get(['primerApellido'])!.value,
      segundoApellido: this.editForm.get(['segundoApellido'])!.value,
      sexo: this.editForm.get(['sexo'])!.value,
      fechaNacimiento: this.editForm.get(['fechaNacimiento'])!.value
        ? moment(this.editForm.get(['fechaNacimiento'])!.value, DATE_TIME_FORMAT)
        : undefined,
      fechaIngreso: this.editForm.get(['fechaIngreso'])!.value
        ? moment(this.editForm.get(['fechaIngreso'])!.value, DATE_TIME_FORMAT)
        : undefined,
      salario: this.editForm.get(['salario'])!.value,
      puesto: this.editForm.get(['puesto'])!.value,
      estado: this.editForm.get(['estado'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmpleado>>): void {
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
