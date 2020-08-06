import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDistributionStore, DistributionStore } from 'app/shared/model/distribution-store.model';
import { DistributionStoreService } from './distribution-store.service';

@Component({
  selector: 'jhi-distribution-store-update',
  templateUrl: './distribution-store-update.component.html',
})
export class DistributionStoreUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    userId: [],
  });

  constructor(
    protected distributionStoreService: DistributionStoreService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ distributionStore }) => {
      this.updateForm(distributionStore);
    });
  }

  updateForm(distributionStore: IDistributionStore): void {
    this.editForm.patchValue({
      id: distributionStore.id,
      name: distributionStore.name,
      userId: distributionStore.userId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const distributionStore = this.createFromForm();
    if (distributionStore.id !== undefined) {
      this.subscribeToSaveResponse(this.distributionStoreService.update(distributionStore));
    } else {
      this.subscribeToSaveResponse(this.distributionStoreService.create(distributionStore));
    }
  }

  private createFromForm(): IDistributionStore {
    return {
      ...new DistributionStore(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      userId: this.editForm.get(['userId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDistributionStore>>): void {
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
