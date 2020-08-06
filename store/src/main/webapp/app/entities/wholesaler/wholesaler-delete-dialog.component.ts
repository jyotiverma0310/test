import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWholesaler } from 'app/shared/model/wholesaler.model';
import { WholesalerService } from './wholesaler.service';

@Component({
  templateUrl: './wholesaler-delete-dialog.component.html',
})
export class WholesalerDeleteDialogComponent {
  wholesaler?: IWholesaler;

  constructor(
    protected wholesalerService: WholesalerService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.wholesalerService.delete(id).subscribe(() => {
      this.eventManager.broadcast('wholesalerListModification');
      this.activeModal.close();
    });
  }
}
