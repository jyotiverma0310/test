import { WalletOperationType } from 'app/shared/model/enumerations/wallet-operation-type.model';

export interface IWallet {
  id?: string;
  orderId?: string;
  currentTotal?: number;
  currentReedem?: number;
  amount?: number;
  operation?: WalletOperationType;
}

export class Wallet implements IWallet {
  constructor(
    public id?: string,
    public orderId?: string,
    public currentTotal?: number,
    public currentReedem?: number,
    public amount?: number,
    public operation?: WalletOperationType
  ) {}
}
