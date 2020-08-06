import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUserLocation, UserLocation } from 'app/shared/model/user-location.model';
import { UserLocationService } from './user-location.service';

@Component({
  selector: 'jhi-user-location-update',
  templateUrl: './user-location-update.component.html',
})
export class UserLocationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    userId: [],
    latitude: [],
    longitude: [],
  });

  constructor(protected userLocationService: UserLocationService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userLocation }) => {
      this.updateForm(userLocation);
    });
  }

  updateForm(userLocation: IUserLocation): void {
    this.editForm.patchValue({
      id: userLocation.id,
      userId: userLocation.userId,
      latitude: userLocation.latitude,
      longitude: userLocation.longitude,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userLocation = this.createFromForm();
    if (userLocation.id !== undefined) {
      this.subscribeToSaveResponse(this.userLocationService.update(userLocation));
    } else {
      this.subscribeToSaveResponse(this.userLocationService.create(userLocation));
    }
  }

  private createFromForm(): IUserLocation {
    return {
      ...new UserLocation(),
      id: this.editForm.get(['id'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      latitude: this.editForm.get(['latitude'])!.value,
      longitude: this.editForm.get(['longitude'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserLocation>>): void {
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
