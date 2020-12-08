import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Examen2SharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [Examen2SharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent],
})
export class Examen2HomeModule {}
