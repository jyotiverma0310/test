import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StoreSharedModule } from 'app/shared/shared.module';
import { DistributionStoreComponent } from './distribution-store.component';
import { DistributionStoreDetailComponent } from './distribution-store-detail.component';
import { DistributionStoreUpdateComponent } from './distribution-store-update.component';
import { DistributionStoreDeleteDialogComponent } from './distribution-store-delete-dialog.component';
import { distributionStoreRoute } from './distribution-store.route';

@NgModule({
  imports: [StoreSharedModule, RouterModule.forChild(distributionStoreRoute)],
  declarations: [
    DistributionStoreComponent,
    DistributionStoreDetailComponent,
    DistributionStoreUpdateComponent,
    DistributionStoreDeleteDialogComponent,
  ],
  entryComponents: [DistributionStoreDeleteDialogComponent],
})
export class StoreDistributionStoreModule {}
