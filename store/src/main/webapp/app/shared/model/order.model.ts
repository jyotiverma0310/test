import { Moment } from 'moment';
import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';
import { OrderType } from 'app/shared/model/enumerations/order-type.model';

export interface IOrder {
  id?: string;
  orderNo?: string;
  userId?: string;
  deliveryEmployeeId?: string;
  status?: OrderStatus;
  orderType?: OrderType;
  isActive?: boolean;
  shippingAddress?: string;
  paidTime?: Moment;
  completeTime?: Moment;
  shippmentTime?: Moment;
  deliveryTime?: Moment;
  cancelTime?: Moment;
  dispatchTime?: Moment;
  rejectTime?: Moment;
  acceptTime?: Moment;
  note?: string;
  rejectNote?: string;
  cancelNote?: string;
}

export class Order implements IOrder {
  constructor(
    public id?: string,
    public orderNo?: string,
    public userId?: string,
    public deliveryEmployeeId?: string,
    public status?: OrderStatus,
    public orderType?: OrderType,
    public isActive?: boolean,
    public shippingAddress?: string,
    public paidTime?: Moment,
    public completeTime?: Moment,
    public shippmentTime?: Moment,
    public deliveryTime?: Moment,
    public cancelTime?: Moment,
    public dispatchTime?: Moment,
    public rejectTime?: Moment,
    public acceptTime?: Moment,
    public note?: string,
    public rejectNote?: string,
    public cancelNote?: string
  ) {
    this.isActive = this.isActive || false;
  }
}
