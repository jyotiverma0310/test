import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StoreTestModule } from '../../../test.module';
import { AddOnProductUpdateComponent } from 'app/entities/add-on-product/add-on-product-update.component';
import { AddOnProductService } from 'app/entities/add-on-product/add-on-product.service';
import { AddOnProduct } from 'app/shared/model/add-on-product.model';

describe('Component Tests', () => {
  describe('AddOnProduct Management Update Component', () => {
    let comp: AddOnProductUpdateComponent;
    let fixture: ComponentFixture<AddOnProductUpdateComponent>;
    let service: AddOnProductService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StoreTestModule],
        declarations: [AddOnProductUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AddOnProductUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AddOnProductUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AddOnProductService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AddOnProduct('123');
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
        const entity = new AddOnProduct();
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
