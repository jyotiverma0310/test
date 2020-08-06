import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUserLocation, UserLocation } from 'app/shared/model/user-location.model';
import { UserLocationService } from './user-location.service';
import { UserLocationComponent } from './user-location.component';
import { UserLocationDetailComponent } from './user-location-detail.component';
import { UserLocationUpdateComponent } from './user-location-update.component';

@Injectable({ providedIn: 'root' })
export class UserLocationResolve implements Resolve<IUserLocation> {
  constructor(private service: UserLocationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserLocation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((userLocation: HttpResponse<UserLocation>) => {
          if (userLocation.body) {
            return of(userLocation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UserLocation());
  }
}

export const userLocationRoute: Routes = [
  {
    path: '',
    component: UserLocationComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'storeApp.userLocation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserLocationDetailComponent,
    resolve: {
      userLocation: UserLocationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.userLocation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UserLocationUpdateComponent,
    resolve: {
      userLocation: UserLocationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.userLocation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UserLocationUpdateComponent,
    resolve: {
      userLocation: UserLocationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.userLocation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
