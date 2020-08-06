import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITermsAndCondition } from 'app/shared/model/terms-and-condition.model';

@Component({
  selector: 'jhi-terms-and-condition-detail',
  templateUrl: './terms-and-condition-detail.component.html',
})
export class TermsAndConditionDetailComponent implements OnInit {
  termsAndCondition: ITermsAndCondition | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ termsAndCondition }) => (this.termsAndCondition = termsAndCondition));
  }

  previousState(): void {
    window.history.back();
  }
}
