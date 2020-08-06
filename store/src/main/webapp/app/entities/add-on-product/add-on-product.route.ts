import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAddOnProduct, AddOnProduct } from 'app/shared/model/add-on-product.model';
import { AddOnProductService } from './add-on-product.service';
import { AddOnProductComponent } from './add-on-product.component';
import { AddOnProductDetailComponent } from './add-on-product-detail.component';
import { AddOnProductUpdateComponent } from './add-on-product-update.component';

@Injectable({ providedIn: 'root' })
export class AddOnProductResolve implements Resolve<IAddOnProduct> {
  constructor(private service: AddOnProductService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAddOnProduct> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((addOnProduct: HttpResponse<AddOnProduct>) => {
          if (addOnProduct.body) {
            return of(addOnProduct.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AddOnProduct());
  }
}

export const addOnProductRoute: Routes = [
  {
    path: '',
    component: AddOnProductComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'storeApp.addOnProduct.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AddOnProductDetailComponent,
    resolve: {
      addOnProduct: AddOnProductResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.addOnProduct.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AddOnProductUpdateComponent,
    resolve: {
      addOnProduct: AddOnProductResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.addOnProduct.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AddOnProductUpdateComponent,
    resolve: {
      addOnProduct: AddOnProductResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.addOnProduct.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
