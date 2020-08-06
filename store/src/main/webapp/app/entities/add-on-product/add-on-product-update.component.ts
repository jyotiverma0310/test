import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAddOnProduct, AddOnProduct } from 'app/shared/model/add-on-product.model';
import { AddOnProductService } from './add-on-product.service';

@Component({
  selector: 'jhi-add-on-product-update',
  templateUrl: './add-on-product-update.component.html',
})
export class AddOnProductUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    isActive: [],
    price: [],
    productCategoryId: [],
  });

  constructor(protected addOnProductService: AddOnProductService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ addOnProduct }) => {
      this.updateForm(addOnProduct);
    });
  }

  updateForm(addOnProduct: IAddOnProduct): void {
    this.editForm.patchValue({
      id: addOnProduct.id,
      name: addOnProduct.name,
      isActive: addOnProduct.isActive,
      price: addOnProduct.price,
      productCategoryId: addOnProduct.productCategoryId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const addOnProduct = this.createFromForm();
    if (addOnProduct.id !== undefined) {
      this.subscribeToSaveResponse(this.addOnProductService.update(addOnProduct));
    } else {
      this.subscribeToSaveResponse(this.addOnProductService.create(addOnProduct));
    }
  }

  private createFromForm(): IAddOnProduct {
    return {
      ...new AddOnProduct(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
      price: this.editForm.get(['price'])!.value,
      productCategoryId: this.editForm.get(['productCategoryId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAddOnProduct>>): void {
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
