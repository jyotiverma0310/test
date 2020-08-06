import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StoreSharedModule } from 'app/shared/shared.module';
import { TermsAndConditionComponent } from './terms-and-condition.component';
import { TermsAndConditionDetailComponent } from './terms-and-condition-detail.component';
import { TermsAndConditionUpdateComponent } from './terms-and-condition-update.component';
import { TermsAndConditionDeleteDialogComponent } from './terms-and-condition-delete-dialog.component';
import { termsAndConditionRoute } from './terms-and-condition.route';

@NgModule({
  imports: [StoreSharedModule, RouterModule.forChild(termsAndConditionRoute)],
  declarations: [
    TermsAndConditionComponent,
    TermsAndConditionDetailComponent,
    TermsAndConditionUpdateComponent,
    TermsAndConditionDeleteDialogComponent,
  ],
  entryComponents: [TermsAndConditionDeleteDialogComponent],
})
export class StoreTermsAndConditionModule {}
