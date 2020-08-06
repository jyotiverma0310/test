import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrder } from 'app/shared/model/order.model';

type EntityResponseType = HttpResponse<IOrder>;
type EntityArrayResponseType = HttpResponse<IOrder[]>;

@Injectable({ providedIn: 'root' })
export class OrderService {
  public resourceUrl = SERVER_API_URL + 'api/orders';

  constructor(protected http: HttpClient) {}

  create(order: IOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(order);
    return this.http
      .post<IOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(order: IOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(order);
    return this.http
      .put<IOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IOrder>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOrder[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(order: IOrder): IOrder {
    const copy: IOrder = Object.assign({}, order, {
      paidTime: order.paidTime && order.paidTime.isValid() ? order.paidTime.toJSON() : undefined,
      completeTime: order.completeTime && order.completeTime.isValid() ? order.completeTime.toJSON() : undefined,
      shippmentTime: order.shippmentTime && order.shippmentTime.isValid() ? order.shippmentTime.toJSON() : undefined,
      deliveryTime: order.deliveryTime && order.deliveryTime.isValid() ? order.deliveryTime.toJSON() : undefined,
      cancelTime: order.cancelTime && order.cancelTime.isValid() ? order.cancelTime.toJSON() : undefined,
      dispatchTime: order.dispatchTime && order.dispatchTime.isValid() ? order.dispatchTime.toJSON() : undefined,
      rejectTime: order.rejectTime && order.rejectTime.isValid() ? order.rejectTime.toJSON() : undefined,
      acceptTime: order.acceptTime && order.acceptTime.isValid() ? order.acceptTime.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.paidTime = res.body.paidTime ? moment(res.body.paidTime) : undefined;
      res.body.completeTime = res.body.completeTime ? moment(res.body.completeTime) : undefined;
      res.body.shippmentTime = res.body.shippmentTime ? moment(res.body.shippmentTime) : undefined;
      res.body.deliveryTime = res.body.deliveryTime ? moment(res.body.deliveryTime) : undefined;
      res.body.cancelTime = res.body.cancelTime ? moment(res.body.cancelTime) : undefined;
      res.body.dispatchTime = res.body.dispatchTime ? moment(res.body.dispatchTime) : undefined;
      res.body.rejectTime = res.body.rejectTime ? moment(res.body.rejectTime) : undefined;
      res.body.acceptTime = res.body.acceptTime ? moment(res.body.acceptTime) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((order: IOrder) => {
        order.paidTime = order.paidTime ? moment(order.paidTime) : undefined;
        order.completeTime = order.completeTime ? moment(order.completeTime) : undefined;
        order.shippmentTime = order.shippmentTime ? moment(order.shippmentTime) : undefined;
        order.deliveryTime = order.deliveryTime ? moment(order.deliveryTime) : undefined;
        order.cancelTime = order.cancelTime ? moment(order.cancelTime) : undefined;
        order.dispatchTime = order.dispatchTime ? moment(order.dispatchTime) : undefined;
        order.rejectTime = order.rejectTime ? moment(order.rejectTime) : undefined;
        order.acceptTime = order.acceptTime ? moment(order.acceptTime) : undefined;
      });
    }
    return res;
  }
}
