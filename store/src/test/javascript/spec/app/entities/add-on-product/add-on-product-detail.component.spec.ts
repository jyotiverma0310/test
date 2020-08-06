import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StoreTestModule } from '../../../test.module';
import { AddOnProductDetailComponent } from 'app/entities/add-on-product/add-on-product-detail.component';
import { AddOnProduct } from 'app/shared/model/add-on-product.model';

describe('Component Tests', () => {
  describe('AddOnProduct Management Detail Component', () => {
    let comp: AddOnProductDetailComponent;
    let fixture: ComponentFixture<AddOnProductDetailComponent>;
    const route = ({ data: of({ addOnProduct: new AddOnProduct('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StoreTestModule],
        declarations: [AddOnProductDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AddOnProductDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AddOnProductDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load addOnProduct on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.addOnProduct).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
