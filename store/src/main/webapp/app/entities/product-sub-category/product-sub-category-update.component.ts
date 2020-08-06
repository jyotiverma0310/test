import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProductSubCategory, ProductSubCategory } from 'app/shared/model/product-sub-category.model';
import { ProductSubCategoryService } from './product-sub-category.service';

@Component({
  selector: 'jhi-product-sub-category-update',
  templateUrl: './product-sub-category-update.component.html',
})
export class ProductSubCategoryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    isActive: [],
  });

  constructor(
    protected productSubCategoryService: ProductSubCategoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productSubCategory }) => {
      this.updateForm(productSubCategory);
    });
  }

  updateForm(productSubCategory: IProductSubCategory): void {
    this.editForm.patchValue({
      id: productSubCategory.id,
      name: productSubCategory.name,
      isActive: productSubCategory.isActive,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const productSubCategory = this.createFromForm();
    if (productSubCategory.id !== undefined) {
      this.subscribeToSaveResponse(this.productSubCategoryService.update(productSubCategory));
    } else {
      this.subscribeToSaveResponse(this.productSubCategoryService.create(productSubCategory));
    }
  }

  private createFromForm(): IProductSubCategory {
    return {
      ...new ProductSubCategory(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductSubCategory>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
