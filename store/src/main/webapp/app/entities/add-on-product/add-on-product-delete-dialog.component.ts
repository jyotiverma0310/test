import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAddOnProduct } from 'app/shared/model/add-on-product.model';
import { AddOnProductService } from './add-on-product.service';

@Component({
  templateUrl: './add-on-product-delete-dialog.component.html',
})
export class AddOnProductDeleteDialogComponent {
  addOnProduct?: IAddOnProduct;

  constructor(
    protected addOnProductService: AddOnProductService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.addOnProductService.delete(id).subscribe(() => {
      this.eventManager.broadcast('addOnProductListModification');
      this.activeModal.close();
    });
  }
}
