import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StoreSharedModule } from 'app/shared/shared.module';
import { ProductSubCategoryComponent } from './product-sub-category.component';
import { ProductSubCategoryDetailComponent } from './product-sub-category-detail.component';
import { ProductSubCategoryUpdateComponent } from './product-sub-category-update.component';
import { ProductSubCategoryDeleteDialogComponent } from './product-sub-category-delete-dialog.component';
import { productSubCategoryRoute } from './product-sub-category.route';

@NgModule({
  imports: [StoreSharedModule, RouterModule.forChild(productSubCategoryRoute)],
  declarations: [
    ProductSubCategoryComponent,
    ProductSubCategoryDetailComponent,
    ProductSubCategoryUpdateComponent,
    ProductSubCategoryDeleteDialogComponent,
  ],
  entryComponents: [ProductSubCategoryDeleteDialogComponent],
})
export class StoreProductSubCategoryModule {}
