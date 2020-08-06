import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFaq, Faq } from 'app/shared/model/faq.model';
import { FaqService } from './faq.service';

@Component({
  selector: 'jhi-faq-update',
  templateUrl: './faq-update.component.html',
})
export class FaqUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    type: [],
    question: [],
    answer: [],
    isActive: [],
  });

  constructor(protected faqService: FaqService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ faq }) => {
      this.updateForm(faq);
    });
  }

  updateForm(faq: IFaq): void {
    this.editForm.patchValue({
      id: faq.id,
      type: faq.type,
      question: faq.question,
      answer: faq.answer,
      isActive: faq.isActive,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const faq = this.createFromForm();
    if (faq.id !== undefined) {
      this.subscribeToSaveResponse(this.faqService.update(faq));
    } else {
      this.subscribeToSaveResponse(this.faqService.create(faq));
    }
  }

  private createFromForm(): IFaq {
    return {
      ...new Faq(),
      id: this.editForm.get(['id'])!.value,
      type: this.editForm.get(['type'])!.value,
      question: this.editForm.get(['question'])!.value,
      answer: this.editForm.get(['answer'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFaq>>): void {
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
