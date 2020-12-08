import { Estado } from 'app/shared/model/enumerations/estado.model';

export interface IPuesto {
  id?: number;
  nombre?: string;
  estado?: Estado;
}

export class Puesto implements IPuesto {
  constructor(public id?: number, public nombre?: string, public estado?: Estado) {}
}
