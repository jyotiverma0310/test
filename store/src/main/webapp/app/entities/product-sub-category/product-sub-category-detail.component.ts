import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductSubCategory } from 'app/shared/model/product-sub-category.model';

@Component({
  selector: 'jhi-product-sub-category-detail',
  templateUrl: './product-sub-category-detail.component.html',
})
export class ProductSubCategoryDetailComponent implements OnInit {
  productSubCategory: IProductSubCategory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productSubCategory }) => (this.productSubCategory = productSubCategory));
  }

  previousState(): void {
    window.history.back();
  }
}
