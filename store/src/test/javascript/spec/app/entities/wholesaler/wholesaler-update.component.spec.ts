import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StoreTestModule } from '../../../test.module';
import { WholesalerUpdateComponent } from 'app/entities/wholesaler/wholesaler-update.component';
import { WholesalerService } from 'app/entities/wholesaler/wholesaler.service';
import { Wholesaler } from 'app/shared/model/wholesaler.model';

describe('Component Tests', () => {
  describe('Wholesaler Management Update Component', () => {
    let comp: WholesalerUpdateComponent;
    let fixture: ComponentFixture<WholesalerUpdateComponent>;
    let service: WholesalerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StoreTestModule],
        declarations: [WholesalerUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(WholesalerUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WholesalerUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WholesalerService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Wholesaler('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Wholesaler();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
