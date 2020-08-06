import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StoreTestModule } from '../../../test.module';
import { TermsAndConditionUpdateComponent } from 'app/entities/terms-and-condition/terms-and-condition-update.component';
import { TermsAndConditionService } from 'app/entities/terms-and-condition/terms-and-condition.service';
import { TermsAndCondition } from 'app/shared/model/terms-and-condition.model';

describe('Component Tests', () => {
  describe('TermsAndCondition Management Update Component', () => {
    let comp: TermsAndConditionUpdateComponent;
    let fixture: ComponentFixture<TermsAndConditionUpdateComponent>;
    let service: TermsAndConditionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StoreTestModule],
        declarations: [TermsAndConditionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TermsAndConditionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TermsAndConditionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TermsAndConditionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TermsAndCondition('123');
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
        const entity = new TermsAndCondition();
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
