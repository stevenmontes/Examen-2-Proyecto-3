import { Moment } from 'moment';
import { IEmpleado } from 'app/shared/model/empleado.model';
import { IDepartamento } from 'app/shared/model/departamento.model';

export interface IJefatura {
  id?: number;
  fechaInicio?: Moment;
  empleado?: IEmpleado;
  departamento?: IDepartamento;
}

export class Jefatura implements IJefatura {
  constructor(public id?: number, public fechaInicio?: Moment, public empleado?: IEmpleado, public departamento?: IDepartamento) {}
}
