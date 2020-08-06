import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDistributionStore } from 'app/shared/model/distribution-store.model';

@Component({
  selector: 'jhi-distribution-store-detail',
  templateUrl: './distribution-store-detail.component.html',
})
export class DistributionStoreDetailComponent implements OnInit {
  distributionStore: IDistributionStore | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ distributionStore }) => (this.distributionStore = distributionStore));
  }

  previousState(): void {
    window.history.back();
  }
}
