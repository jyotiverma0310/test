import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWholesaler, Wholesaler } from 'app/shared/model/wholesaler.model';
import { WholesalerService } from './wholesaler.service';
import { WholesalerComponent } from './wholesaler.component';
import { WholesalerDetailComponent } from './wholesaler-detail.component';
import { WholesalerUpdateComponent } from './wholesaler-update.component';

@Injectable({ providedIn: 'root' })
export class WholesalerResolve implements Resolve<IWholesaler> {
  constructor(private service: WholesalerService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWholesaler> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((wholesaler: HttpResponse<Wholesaler>) => {
          if (wholesaler.body) {
            return of(wholesaler.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Wholesaler());
  }
}

export const wholesalerRoute: Routes = [
  {
    path: '',
    component: WholesalerComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'storeApp.wholesaler.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: WholesalerDetailComponent,
    resolve: {
      wholesaler: WholesalerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.wholesaler.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: WholesalerUpdateComponent,
    resolve: {
      wholesaler: WholesalerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.wholesaler.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: WholesalerUpdateComponent,
    resolve: {
      wholesaler: WholesalerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.wholesaler.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
