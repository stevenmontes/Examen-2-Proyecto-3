import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'empleado',
        loadChildren: () => import('./empleado/empleado.module').then(m => m.Examen2EmpleadoModule),
      },
      {
        path: 'departamento',
        loadChildren: () => import('./departamento/departamento.module').then(m => m.Examen2DepartamentoModule),
      },
      {
        path: 'jefatura',
        loadChildren: () => import('./jefatura/jefatura.module').then(m => m.Examen2JefaturaModule),
      },
      {
        path: 'puesto',
        loadChildren: () => import('./puesto/puesto.module').then(m => m.Examen2PuestoModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class Examen2EntityModule {}
