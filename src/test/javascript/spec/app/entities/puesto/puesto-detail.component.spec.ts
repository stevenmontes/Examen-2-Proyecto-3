import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Examen2TestModule } from '../../../test.module';
import { PuestoDetailComponent } from 'app/entities/puesto/puesto-detail.component';
import { Puesto } from 'app/shared/model/puesto.model';

describe('Component Tests', () => {
  describe('Puesto Management Detail Component', () => {
    let comp: PuestoDetailComponent;
    let fixture: ComponentFixture<PuestoDetailComponent>;
    const route = ({ data: of({ puesto: new Puesto(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Examen2TestModule],
        declarations: [PuestoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PuestoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PuestoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load puesto on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.puesto).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
