import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StoreSharedModule } from 'app/shared/shared.module';
import { AddOnProductComponent } from './add-on-product.component';
import { AddOnProductDetailComponent } from './add-on-product-detail.component';
import { AddOnProductUpdateComponent } from './add-on-product-update.component';
import { AddOnProductDeleteDialogComponent } from './add-on-product-delete-dialog.component';
import { addOnProductRoute } from './add-on-product.route';

@NgModule({
  imports: [StoreSharedModule, RouterModule.forChild(addOnProductRoute)],
  declarations: [AddOnProductComponent, AddOnProductDetailComponent, AddOnProductUpdateComponent, AddOnProductDeleteDialogComponent],
  entryComponents: [AddOnProductDeleteDialogComponent],
})
export class StoreAddOnProductModule {}
