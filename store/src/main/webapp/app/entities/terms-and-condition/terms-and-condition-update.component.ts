import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITermsAndCondition, TermsAndCondition } from 'app/shared/model/terms-and-condition.model';
import { TermsAndConditionService } from './terms-and-condition.service';

@Component({
  selector: 'jhi-terms-and-condition-update',
  templateUrl: './terms-and-condition-update.component.html',
})
export class TermsAndConditionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    note: [],
    isActive: [],
  });

  constructor(
    protected termsAndConditionService: TermsAndConditionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ termsAndCondition }) => {
      this.updateForm(termsAndCondition);
    });
  }

  updateForm(termsAndCondition: ITermsAndCondition): void {
    this.editForm.patchValue({
      id: termsAndCondition.id,
      note: termsAndCondition.note,
      isActive: termsAndCondition.isActive,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const termsAndCondition = this.createFromForm();
    if (termsAndCondition.id !== undefined) {
      this.subscribeToSaveResponse(this.termsAndConditionService.update(termsAndCondition));
    } else {
      this.subscribeToSaveResponse(this.termsAndConditionService.create(termsAndCondition));
    }
  }

  private createFromForm(): ITermsAndCondition {
    return {
      ...new TermsAndCondition(),
      id: this.editForm.get(['id'])!.value,
      note: this.editForm.get(['note'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITermsAndCondition>>): void {
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
