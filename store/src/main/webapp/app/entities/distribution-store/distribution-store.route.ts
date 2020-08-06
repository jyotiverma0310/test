import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDistributionStore, DistributionStore } from 'app/shared/model/distribution-store.model';
import { DistributionStoreService } from './distribution-store.service';
import { DistributionStoreComponent } from './distribution-store.component';
import { DistributionStoreDetailComponent } from './distribution-store-detail.component';
import { DistributionStoreUpdateComponent } from './distribution-store-update.component';

@Injectable({ providedIn: 'root' })
export class DistributionStoreResolve implements Resolve<IDistributionStore> {
  constructor(private service: DistributionStoreService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDistributionStore> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((distributionStore: HttpResponse<DistributionStore>) => {
          if (distributionStore.body) {
            return of(distributionStore.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DistributionStore());
  }
}

export const distributionStoreRoute: Routes = [
  {
    path: '',
    component: DistributionStoreComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'storeApp.distributionStore.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DistributionStoreDetailComponent,
    resolve: {
      distributionStore: DistributionStoreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.distributionStore.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DistributionStoreUpdateComponent,
    resolve: {
      distributionStore: DistributionStoreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.distributionStore.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DistributionStoreUpdateComponent,
    resolve: {
      distributionStore: DistributionStoreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.distributionStore.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
