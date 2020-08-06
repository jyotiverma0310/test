import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StoreSharedModule } from 'app/shared/shared.module';
import { UserLocationComponent } from './user-location.component';
import { UserLocationDetailComponent } from './user-location-detail.component';
import { UserLocationUpdateComponent } from './user-location-update.component';
import { UserLocationDeleteDialogComponent } from './user-location-delete-dialog.component';
import { userLocationRoute } from './user-location.route';

@NgModule({
  imports: [StoreSharedModule, RouterModule.forChild(userLocationRoute)],
  declarations: [UserLocationComponent, UserLocationDetailComponent, UserLocationUpdateComponent, UserLocationDeleteDialogComponent],
  entryComponents: [UserLocationDeleteDialogComponent],
})
export class StoreUserLocationModule {}
