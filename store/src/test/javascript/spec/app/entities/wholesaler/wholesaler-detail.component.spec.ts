import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StoreTestModule } from '../../../test.module';
import { WholesalerDetailComponent } from 'app/entities/wholesaler/wholesaler-detail.component';
import { Wholesaler } from 'app/shared/model/wholesaler.model';

describe('Component Tests', () => {
  describe('Wholesaler Management Detail Component', () => {
    let comp: WholesalerDetailComponent;
    let fixture: ComponentFixture<WholesalerDetailComponent>;
    const route = ({ data: of({ wholesaler: new Wholesaler('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StoreTestModule],
        declarations: [WholesalerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(WholesalerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WholesalerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load wholesaler on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.wholesaler).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
