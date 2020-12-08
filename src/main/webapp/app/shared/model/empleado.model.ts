import { Moment } from 'moment';
import { IJefatura } from 'app/shared/model/jefatura.model';
import { IDepartamento } from 'app/shared/model/departamento.model';
import { Sexo } from 'app/shared/model/enumerations/sexo.model';
import { Puesto } from 'app/shared/model/enumerations/puesto.model';
import { Estado } from 'app/shared/model/enumerations/estado.model';

export interface IEmpleado {
  id?: number;
  nombre?: string;
  primerApellido?: string;
  segundoApellido?: string;
  sexo?: Sexo;
  fechaNacimiento?: Moment;
  fechaIngreso?: Moment;
  salario?: number;
  puesto?: Puesto;
  estado?: Estado;
  jefatura?: IJefatura;
  departamentos?: IDepartamento[];
}

export class Empleado implements IEmpleado {
  constructor(
    public id?: number,
    public nombre?: string,
    public primerApellido?: string,
    public segundoApellido?: string,
    public sexo?: Sexo,
    public fechaNacimiento?: Moment,
    public fechaIngreso?: Moment,
    public salario?: number,
    public puesto?: Puesto,
    public estado?: Estado,
    public jefatura?: IJefatura,
    public departamentos?: IDepartamento[]
  ) {}
}
