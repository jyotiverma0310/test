import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StoreTestModule } from '../../../test.module';
import { UserLocationUpdateComponent } from 'app/entities/user-location/user-location-update.component';
import { UserLocationService } from 'app/entities/user-location/user-location.service';
import { UserLocation } from 'app/shared/model/user-location.model';

describe('Component Tests', () => {
  describe('UserLocation Management Update Component', () => {
    let comp: UserLocationUpdateComponent;
    let fixture: ComponentFixture<UserLocationUpdateComponent>;
    let service: UserLocationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StoreTestModule],
        declarations: [UserLocationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(UserLocationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserLocationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserLocationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserLocation('123');
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
        const entity = new UserLocation();
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
