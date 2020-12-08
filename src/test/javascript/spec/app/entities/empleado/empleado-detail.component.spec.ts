import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Examen2TestModule } from '../../../test.module';
import { EmpleadoDetailComponent } from 'app/entities/empleado/empleado-detail.component';
import { Empleado } from 'app/shared/model/empleado.model';

describe('Component Tests', () => {
  describe('Empleado Management Detail Component', () => {
    let comp: EmpleadoDetailComponent;
    let fixture: ComponentFixture<EmpleadoDetailComponent>;
    const route = ({ data: of({ empleado: new Empleado(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Examen2TestModule],
        declarations: [EmpleadoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EmpleadoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EmpleadoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load empleado on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.empleado).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});