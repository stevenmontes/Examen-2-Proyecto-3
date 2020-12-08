import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPuesto, Puesto } from 'app/shared/model/puesto.model';
import { PuestoService } from './puesto.service';
import { PuestoComponent } from './puesto.component';
import { PuestoDetailComponent } from './puesto-detail.component';
import { PuestoUpdateComponent } from './puesto-update.component';

@Injectable({ providedIn: 'root' })
export class PuestoResolve implements Resolve<IPuesto> {
  constructor(private service: PuestoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPuesto> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((puesto: HttpResponse<Puesto>) => {
          if (puesto.body) {
            return of(puesto.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Puesto());
  }
}

export const puestoRoute: Routes = [
  {
    path: '',
    component: PuestoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'examen2App.puesto.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PuestoDetailComponent,
    resolve: {
      puesto: PuestoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'examen2App.puesto.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PuestoUpdateComponent,
    resolve: {
      puesto: PuestoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'examen2App.puesto.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PuestoUpdateComponent,
    resolve: {
      puesto: PuestoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'examen2App.puesto.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
