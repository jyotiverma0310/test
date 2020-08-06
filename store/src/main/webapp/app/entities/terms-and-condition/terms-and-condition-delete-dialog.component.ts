import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITermsAndCondition } from 'app/shared/model/terms-and-condition.model';
import { TermsAndConditionService } from './terms-and-condition.service';

@Component({
  templateUrl: './terms-and-condition-delete-dialog.component.html',
})
export class TermsAndConditionDeleteDialogComponent {
  termsAndCondition?: ITermsAndCondition;

  constructor(
    protected termsAndConditionService: TermsAndConditionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.termsAndConditionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('termsAndConditionListModification');
      this.activeModal.close();
    });
  }
}
