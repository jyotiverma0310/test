import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOrder, Order } from 'app/shared/model/order.model';
import { OrderService } from './order.service';

@Component({
  selector: 'jhi-order-update',
  templateUrl: './order-update.component.html',
})
export class OrderUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    orderNo: [],
    userId: [],
    deliveryEmployeeId: [],
    status: [],
    orderType: [],
    isActive: [],
    shippingAddress: [],
    paidTime: [],
    completeTime: [],
    shippmentTime: [],
    deliveryTime: [],
    cancelTime: [],
    dispatchTime: [],
    rejectTime: [],
    acceptTime: [],
    note: [],
    rejectNote: [],
    cancelNote: [],
  });

  constructor(protected orderService: OrderService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ order }) => {
      if (!order.id) {
        const today = moment().startOf('day');
        order.paidTime = today;
        order.completeTime = today;
        order.shippmentTime = today;
        order.deliveryTime = today;
        order.cancelTime = today;
        order.dispatchTime = today;
        order.rejectTime = today;
        order.acceptTime = today;
      }

      this.updateForm(order);
    });
  }

  updateForm(order: IOrder): void {
    this.editForm.patchValue({
      id: order.id,
      orderNo: order.orderNo,
      userId: order.userId,
      deliveryEmployeeId: order.deliveryEmployeeId,
      status: order.status,
      orderType: order.orderType,
      isActive: order.isActive,
      shippingAddress: order.shippingAddress,
      paidTime: order.paidTime ? order.paidTime.format(DATE_TIME_FORMAT) : null,
      completeTime: order.completeTime ? order.completeTime.format(DATE_TIME_FORMAT) : null,
      shippmentTime: order.shippmentTime ? order.shippmentTime.format(DATE_TIME_FORMAT) : null,
      deliveryTime: order.deliveryTime ? order.deliveryTime.format(DATE_TIME_FORMAT) : null,
      cancelTime: order.cancelTime ? order.cancelTime.format(DATE_TIME_FORMAT) : null,
      dispatchTime: order.dispatchTime ? order.dispatchTime.format(DATE_TIME_FORMAT) : null,
      rejectTime: order.rejectTime ? order.rejectTime.format(DATE_TIME_FORMAT) : null,
      acceptTime: order.acceptTime ? order.acceptTime.format(DATE_TIME_FORMAT) : null,
      note: order.note,
      rejectNote: order.rejectNote,
      cancelNote: order.cancelNote,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const order = this.createFromForm();
    if (order.id !== undefined) {
      this.subscribeToSaveResponse(this.orderService.update(order));
    } else {
      this.subscribeToSaveResponse(this.orderService.create(order));
    }
  }

  private createFromForm(): IOrder {
    return {
      ...new Order(),
      id: this.editForm.get(['id'])!.value,
      orderNo: this.editForm.get(['orderNo'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      deliveryEmployeeId: this.editForm.get(['deliveryEmployeeId'])!.value,
      status: this.editForm.get(['status'])!.value,
      orderType: this.editForm.get(['orderType'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
      shippingAddress: this.editForm.get(['shippingAddress'])!.value,
      paidTime: this.editForm.get(['paidTime'])!.value ? moment(this.editForm.get(['paidTime'])!.value, DATE_TIME_FORMAT) : undefined,
      completeTime: this.editForm.get(['completeTime'])!.value
        ? moment(this.editForm.get(['completeTime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      shippmentTime: this.editForm.get(['shippmentTime'])!.value
        ? moment(this.editForm.get(['shippmentTime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      deliveryTime: this.editForm.get(['deliveryTime'])!.value
        ? moment(this.editForm.get(['deliveryTime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      cancelTime: this.editForm.get(['cancelTime'])!.value ? moment(this.editForm.get(['cancelTime'])!.value, DATE_TIME_FORMAT) : undefined,
      dispatchTime: this.editForm.get(['dispatchTime'])!.value
        ? moment(this.editForm.get(['dispatchTime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      rejectTime: this.editForm.get(['rejectTime'])!.value ? moment(this.editForm.get(['rejectTime'])!.value, DATE_TIME_FORMAT) : undefined,
      acceptTime: this.editForm.get(['acceptTime'])!.value ? moment(this.editForm.get(['acceptTime'])!.value, DATE_TIME_FORMAT) : undefined,
      note: this.editForm.get(['note'])!.value,
      rejectNote: this.editForm.get(['rejectNote'])!.value,
      cancelNote: this.editForm.get(['cancelNote'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrder>>): void {
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
