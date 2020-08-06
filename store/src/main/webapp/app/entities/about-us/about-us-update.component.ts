import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAboutUs, AboutUs } from 'app/shared/model/about-us.model';
import { AboutUsService } from './about-us.service';

@Component({
  selector: 'jhi-about-us-update',
  templateUrl: './about-us-update.component.html',
})
export class AboutUsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    note: [],
    isActive: [],
  });

  constructor(protected aboutUsService: AboutUsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ aboutUs }) => {
      this.updateForm(aboutUs);
    });
  }

  updateForm(aboutUs: IAboutUs): void {
    this.editForm.patchValue({
      id: aboutUs.id,
      note: aboutUs.note,
      isActive: aboutUs.isActive,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const aboutUs = this.createFromForm();
    if (aboutUs.id !== undefined) {
      this.subscribeToSaveResponse(this.aboutUsService.update(aboutUs));
    } else {
      this.subscribeToSaveResponse(this.aboutUsService.create(aboutUs));
    }
  }

  private createFromForm(): IAboutUs {
    return {
      ...new AboutUs(),
      id: this.editForm.get(['id'])!.value,
      note: this.editForm.get(['note'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAboutUs>>): void {
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
