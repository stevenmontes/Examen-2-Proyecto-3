import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { Examen2SharedModule } from 'app/shared/shared.module';
import { Examen2CoreModule } from 'app/core/core.module';
import { Examen2AppRoutingModule } from './app-routing.module';
import { Examen2HomeModule } from './home/home.module';
import { Examen2EntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    Examen2SharedModule,
    Examen2CoreModule,
    Examen2HomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    Examen2EntityModule,
    Examen2AppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class Examen2AppModule {}
