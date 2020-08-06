import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAddOnProduct } from 'app/shared/model/add-on-product.model';

@Component({
  selector: 'jhi-add-on-product-detail',
  templateUrl: './add-on-product-detail.component.html',
})
export class AddOnProductDetailComponent implements OnInit {
  addOnProduct: IAddOnProduct | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ addOnProduct }) => (this.addOnProduct = addOnProduct));
  }

  previousState(): void {
    window.history.back();
  }
}
