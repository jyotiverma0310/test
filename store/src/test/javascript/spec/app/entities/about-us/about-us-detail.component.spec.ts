import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StoreTestModule } from '../../../test.module';
import { AboutUsDetailComponent } from 'app/entities/about-us/about-us-detail.component';
import { AboutUs } from 'app/shared/model/about-us.model';

describe('Component Tests', () => {
  describe('AboutUs Management Detail Component', () => {
    let comp: AboutUsDetailComponent;
    let fixture: ComponentFixture<AboutUsDetailComponent>;
    const route = ({ data: of({ aboutUs: new AboutUs('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StoreTestModule],
        declarations: [AboutUsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AboutUsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AboutUsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load aboutUs on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.aboutUs).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
