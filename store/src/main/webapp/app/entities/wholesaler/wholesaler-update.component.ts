import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWholesaler, Wholesaler } from 'app/shared/model/wholesaler.model';
import { WholesalerService } from './wholesaler.service';

@Component({
  selector: 'jhi-wholesaler-update',
  templateUrl: './wholesaler-update.component.html',
})
export class WholesalerUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    userId: [],
  });

  constructor(protected wholesalerService: WholesalerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ wholesaler }) => {
      this.updateForm(wholesaler);
    });
  }

  updateForm(wholesaler: IWholesaler): void {
    this.editForm.patchValue({
      id: wholesaler.id,
      name: wholesaler.name,
      userId: wholesaler.userId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const wholesaler = this.createFromForm();
    if (wholesaler.id !== undefined) {
      this.subscribeToSaveResponse(this.wholesalerService.update(wholesaler));
    } else {
      this.subscribeToSaveResponse(this.wholesalerService.create(wholesaler));
    }
  }

  private createFromForm(): IWholesaler {
    return {
      ...new Wholesaler(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      userId: this.editForm.get(['userId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWholesaler>>): void {
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
