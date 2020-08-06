import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWallet, Wallet } from 'app/shared/model/wallet.model';
import { WalletService } from './wallet.service';

@Component({
  selector: 'jhi-wallet-update',
  templateUrl: './wallet-update.component.html',
})
export class WalletUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    orderId: [],
    currentTotal: [],
    currentReedem: [],
    amount: [],
    operation: [],
  });

  constructor(protected walletService: WalletService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ wallet }) => {
      this.updateForm(wallet);
    });
  }

  updateForm(wallet: IWallet): void {
    this.editForm.patchValue({
      id: wallet.id,
      orderId: wallet.orderId,
      currentTotal: wallet.currentTotal,
      currentReedem: wallet.currentReedem,
      amount: wallet.amount,
      operation: wallet.operation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const wallet = this.createFromForm();
    if (wallet.id !== undefined) {
      this.subscribeToSaveResponse(this.walletService.update(wallet));
    } else {
      this.subscribeToSaveResponse(this.walletService.create(wallet));
    }
  }

  private createFromForm(): IWallet {
    return {
      ...new Wallet(),
      id: this.editForm.get(['id'])!.value,
      orderId: this.editForm.get(['orderId'])!.value,
      currentTotal: this.editForm.get(['currentTotal'])!.value,
      currentReedem: this.editForm.get(['currentReedem'])!.value,
      amount: this.editForm.get(['amount'])!.value,
      operation: this.editForm.get(['operation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWallet>>): void {
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
