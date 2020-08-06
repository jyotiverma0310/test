import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserLocation } from 'app/shared/model/user-location.model';
import { UserLocationService } from './user-location.service';

@Component({
  templateUrl: './user-location-delete-dialog.component.html',
})
export class UserLocationDeleteDialogComponent {
  userLocation?: IUserLocation;

  constructor(
    protected userLocationService: UserLocationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.userLocationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userLocationListModification');
      this.activeModal.close();
    });
  }
}
