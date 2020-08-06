import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDistributionStore } from 'app/shared/model/distribution-store.model';
import { DistributionStoreService } from './distribution-store.service';

@Component({
  templateUrl: './distribution-store-delete-dialog.component.html',
})
export class DistributionStoreDeleteDialogComponent {
  distributionStore?: IDistributionStore;

  constructor(
    protected distributionStoreService: DistributionStoreService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.distributionStoreService.delete(id).subscribe(() => {
      this.eventManager.broadcast('distributionStoreListModification');
      this.activeModal.close();
    });
  }
}
