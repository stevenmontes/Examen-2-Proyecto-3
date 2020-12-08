import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Examen2TestModule } from '../../../test.module';
import { PuestoComponent } from 'app/entities/puesto/puesto.component';
import { PuestoService } from 'app/entities/puesto/puesto.service';
import { Puesto } from 'app/shared/model/puesto.model';

describe('Component Tests', () => {
  describe('Puesto Management Component', () => {
    let comp: PuestoComponent;
    let fixture: ComponentFixture<PuestoComponent>;
    let service: PuestoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Examen2TestModule],
        declarations: [PuestoComponent],
      })
        .overrideTemplate(PuestoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PuestoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PuestoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Puesto(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.puestos && comp.puestos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
