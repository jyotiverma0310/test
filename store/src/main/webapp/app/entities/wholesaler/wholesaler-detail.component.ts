import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWholesaler } from 'app/shared/model/wholesaler.model';

@Component({
  selector: 'jhi-wholesaler-detail',
  templateUrl: './wholesaler-detail.component.html',
})
export class WholesalerDetailComponent implements OnInit {
  wholesaler: IWholesaler | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ wholesaler }) => (this.wholesaler = wholesaler));
  }

  previousState(): void {
    window.history.back();
  }
}
