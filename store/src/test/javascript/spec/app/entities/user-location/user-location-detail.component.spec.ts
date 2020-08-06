import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StoreTestModule } from '../../../test.module';
import { UserLocationDetailComponent } from 'app/entities/user-location/user-location-detail.component';
import { UserLocation } from 'app/shared/model/user-location.model';

describe('Component Tests', () => {
  describe('UserLocation Management Detail Component', () => {
    let comp: UserLocationDetailComponent;
    let fixture: ComponentFixture<UserLocationDetailComponent>;
    const route = ({ data: of({ userLocation: new UserLocation('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StoreTestModule],
        declarations: [UserLocationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UserLocationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserLocationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userLocation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userLocation).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
