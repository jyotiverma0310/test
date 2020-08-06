import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProductSubCategory, ProductSubCategory } from 'app/shared/model/product-sub-category.model';
import { ProductSubCategoryService } from './product-sub-category.service';
import { ProductSubCategoryComponent } from './product-sub-category.component';
import { ProductSubCategoryDetailComponent } from './product-sub-category-detail.component';
import { ProductSubCategoryUpdateComponent } from './product-sub-category-update.component';

@Injectable({ providedIn: 'root' })
export class ProductSubCategoryResolve implements Resolve<IProductSubCategory> {
  constructor(private service: ProductSubCategoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProductSubCategory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((productSubCategory: HttpResponse<ProductSubCategory>) => {
          if (productSubCategory.body) {
            return of(productSubCategory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProductSubCategory());
  }
}

export const productSubCategoryRoute: Routes = [
  {
    path: '',
    component: ProductSubCategoryComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'storeApp.productSubCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProductSubCategoryDetailComponent,
    resolve: {
      productSubCategory: ProductSubCategoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.productSubCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProductSubCategoryUpdateComponent,
    resolve: {
      productSubCategory: ProductSubCategoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.productSubCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProductSubCategoryUpdateComponent,
    resolve: {
      productSubCategory: ProductSubCategoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'storeApp.productSubCategory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
