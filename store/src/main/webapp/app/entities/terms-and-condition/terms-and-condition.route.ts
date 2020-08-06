import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITermsAndCondition, TermsAndCondition } from 'app/shared/model/terms-and-condition.model';
import { TermsAndConditionService } from './terms-and-condition.service';
import { TermsAndConditionComponent } from './terms-and-condition.component';
import { TermsAndConditionDetailComponent } from './terms-and-condition-detail.component';
import { TermsAndConditionUpdateComponent } from './terms-and-condition-update.component';

@Injectable({ providedIn: 'root' })
export class TermsAndConditionResolve implements Resolve<ITermsAndCondition> {
  constructor(private service: TermsAndConditionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITermsAndCondition> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((termsAndCondition: HttpResponse<TermsAndCondition>) => {
          if (termsAndCondition.body) {
            return of(termsAndCondition.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TermsAndCondition());
  }
}

export const termsAndConditionRoute: Routes = [
  {
    path: '',
    component: TermsAndConditionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'storeApp.termsAndCondition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TermsAndConditionDetailComponent,
    resolve: {
      termsAndCondition: TermsAndConditionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.termsAndCondition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TermsAndConditionUpdateComponent,
    resolve: {
      termsAndCondition: TermsAndConditionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.termsAndCondition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TermsAndConditionUpdateComponent,
    resolve: {
      termsAndCondition: TermsAndConditionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.termsAndCondition.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
