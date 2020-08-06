import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StoreSharedModule } from 'app/shared/shared.module';
import { ContactUsComponent } from './contact-us.component';
import { ContactUsDetailComponent } from './contact-us-detail.component';
import { ContactUsUpdateComponent } from './contact-us-update.component';
import { ContactUsDeleteDialogComponent } from './contact-us-delete-dialog.component';
import { contactUsRoute } from './contact-us.route';

@NgModule({
  imports: [StoreSharedModule, RouterModule.forChild(contactUsRoute)],
  declarations: [ContactUsComponent, ContactUsDetailComponent, ContactUsUpdateComponent, ContactUsDeleteDialogComponent],
  entryComponents: [ContactUsDeleteDialogComponent],
})
export class StoreContactUsModule {}
