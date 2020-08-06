import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductSubCategory } from 'app/shared/model/product-sub-category.model';
import { ProductSubCategoryService } from './product-sub-category.service';

@Component({
  templateUrl: './product-sub-category-delete-dialog.component.html',
})
export class ProductSubCategoryDeleteDialogComponent {
  productSubCategory?: IProductSubCategory;

  constructor(
    protected productSubCategoryService: ProductSubCategoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.productSubCategoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('productSubCategoryListModification');
      this.activeModal.close();
    });
  }
}
