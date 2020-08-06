import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StoreTestModule } from '../../../test.module';
import { DistributionStoreUpdateComponent } from 'app/entities/distribution-store/distribution-store-update.component';
import { DistributionStoreService } from 'app/entities/distribution-store/distribution-store.service';
import { DistributionStore } from 'app/shared/model/distribution-store.model';

describe('Component Tests', () => {
  describe('DistributionStore Management Update Component', () => {
    let comp: DistributionStoreUpdateComponent;
    let fixture: ComponentFixture<DistributionStoreUpdateComponent>;
    let service: DistributionStoreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StoreTestModule],
        declarations: [DistributionStoreUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DistributionStoreUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DistributionStoreUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DistributionStoreService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DistributionStore('123');
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
        const entity = new DistributionStore();
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
