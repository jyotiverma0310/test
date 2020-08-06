import { TransactionType } from 'app/shared/model/enumerations/transaction-type.model';
import { TransactionUser } from 'app/shared/model/enumerations/transaction-user.model';

export interface ITransactions {
  id?: string;
  orderId?: string;
  amount?: number;
  transactionType?: TransactionType;
  transactionUser?: TransactionUser;
  userId?: string;
}

export class Transactions implements ITransactions {
  constructor(
    public id?: string,
    public orderId?: string,
    public amount?: number,
    public transactionType?: TransactionType,
    public transactionUser?: TransactionUser,
    public userId?: string
  ) {}
}
