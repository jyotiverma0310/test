import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StoreTestModule } from '../../../test.module';
import { DistributionStoreDetailComponent } from 'app/entities/distribution-store/distribution-store-detail.component';
import { DistributionStore } from 'app/shared/model/distribution-store.model';

describe('Component Tests', () => {
  describe('DistributionStore Management Detail Component', () => {
    let comp: DistributionStoreDetailComponent;
    let fixture: ComponentFixture<DistributionStoreDetailComponent>;
    const route = ({ data: of({ distributionStore: new DistributionStore('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StoreTestModule],
        declarations: [DistributionStoreDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DistributionStoreDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DistributionStoreDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load distributionStore on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.distributionStore).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
