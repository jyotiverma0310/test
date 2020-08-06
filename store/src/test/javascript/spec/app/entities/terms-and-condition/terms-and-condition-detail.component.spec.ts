import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StoreTestModule } from '../../../test.module';
import { TermsAndConditionDetailComponent } from 'app/entities/terms-and-condition/terms-and-condition-detail.component';
import { TermsAndCondition } from 'app/shared/model/terms-and-condition.model';

describe('Component Tests', () => {
  describe('TermsAndCondition Management Detail Component', () => {
    let comp: TermsAndConditionDetailComponent;
    let fixture: ComponentFixture<TermsAndConditionDetailComponent>;
    const route = ({ data: of({ termsAndCondition: new TermsAndCondition('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StoreTestModule],
        declarations: [TermsAndConditionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TermsAndConditionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TermsAndConditionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load termsAndCondition on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.termsAndCondition).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
