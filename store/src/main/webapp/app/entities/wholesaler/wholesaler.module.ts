import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StoreSharedModule } from 'app/shared/shared.module';
import { WholesalerComponent } from './wholesaler.component';
import { WholesalerDetailComponent } from './wholesaler-detail.component';
import { WholesalerUpdateComponent } from './wholesaler-update.component';
import { WholesalerDeleteDialogComponent } from './wholesaler-delete-dialog.component';
import { wholesalerRoute } from './wholesaler.route';

@NgModule({
  imports: [StoreSharedModule, RouterModule.forChild(wholesalerRoute)],
  declarations: [WholesalerComponent, WholesalerDetailComponent, WholesalerUpdateComponent, WholesalerDeleteDialogComponent],
  entryComponents: [WholesalerDeleteDialogComponent],
})
export class StoreWholesalerModule {}
