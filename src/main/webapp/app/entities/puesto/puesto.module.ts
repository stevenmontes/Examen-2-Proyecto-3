import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Examen2SharedModule } from 'app/shared/shared.module';
import { PuestoComponent } from './puesto.component';
import { PuestoDetailComponent } from './puesto-detail.component';
import { PuestoUpdateComponent } from './puesto-update.component';
import { PuestoDeleteDialogComponent } from './puesto-delete-dialog.component';
import { puestoRoute } from './puesto.route';

@NgModule({
  imports: [Examen2SharedModule, RouterModule.forChild(puestoRoute)],
  declarations: [PuestoComponent, PuestoDetailComponent, PuestoUpdateComponent, PuestoDeleteDialogComponent],
  entryComponents: [PuestoDeleteDialogComponent],
})
export class Examen2PuestoModule {}
