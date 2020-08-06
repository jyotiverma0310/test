import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { OrderService } from 'app/entities/order/order.service';
import { IOrder, Order } from 'app/shared/model/order.model';
import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';
import { OrderType } from 'app/shared/model/enumerations/order-type.model';

describe('Service Tests', () => {
  describe('Order Service', () => {
    let injector: TestBed;
    let service: OrderService;
    let httpMock: HttpTestingController;
    let elemDefault: IOrder;
    let expectedResult: IOrder | IOrder[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OrderService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Order(
        'ID',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        OrderStatus.Shipped,
        OrderType.PickUp,
        false,
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            paidTime: currentDate.format(DATE_TIME_FORMAT),
            completeTime: currentDate.format(DATE_TIME_FORMAT),
            shippmentTime: currentDate.format(DATE_TIME_FORMAT),
            deliveryTime: currentDate.format(DATE_TIME_FORMAT),
            cancelTime: currentDate.format(DATE_TIME_FORMAT),
            dispatchTime: currentDate.format(DATE_TIME_FORMAT),
            rejectTime: currentDate.format(DATE_TIME_FORMAT),
            acceptTime: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Order', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            paidTime: currentDate.format(DATE_TIME_FORMAT),
            completeTime: currentDate.format(DATE_TIME_FORMAT),
            shippmentTime: currentDate.format(DATE_TIME_FORMAT),
            deliveryTime: currentDate.format(DATE_TIME_FORMAT),
            cancelTime: currentDate.format(DATE_TIME_FORMAT),
            dispatchTime: currentDate.format(DATE_TIME_FORMAT),
            rejectTime: currentDate.format(DATE_TIME_FORMAT),
            acceptTime: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            paidTime: currentDate,
            completeTime: currentDate,
            shippmentTime: currentDate,
            deliveryTime: currentDate,
            cancelTime: currentDate,
            dispatchTime: currentDate,
            rejectTime: currentDate,
            acceptTime: currentDate,
          },
          returnedFromService
        );

        service.create(new Order()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Order', () => {
        const returnedFromService = Object.assign(
          {
            orderNo: 'BBBBBB',
            userId: 'BBBBBB',
            deliveryEmployeeId: 'BBBBBB',
            status: 'BBBBBB',
            orderType: 'BBBBBB',
            isActive: true,
            shippingAddress: 'BBBBBB',
            paidTime: currentDate.format(DATE_TIME_FORMAT),
            completeTime: currentDate.format(DATE_TIME_FORMAT),
            shippmentTime: currentDate.format(DATE_TIME_FORMAT),
            deliveryTime: currentDate.format(DATE_TIME_FORMAT),
            cancelTime: currentDate.format(DATE_TIME_FORMAT),
            dispatchTime: currentDate.format(DATE_TIME_FORMAT),
            rejectTime: currentDate.format(DATE_TIME_FORMAT),
            acceptTime: currentDate.format(DATE_TIME_FORMAT),
            note: 'BBBBBB',
            rejectNote: 'BBBBBB',
            cancelNote: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            paidTime: currentDate,
            completeTime: currentDate,
            shippmentTime: currentDate,
            deliveryTime: currentDate,
            cancelTime: currentDate,
            dispatchTime: currentDate,
            rejectTime: currentDate,
            acceptTime: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Order', () => {
        const returnedFromService = Object.assign(
          {
            orderNo: 'BBBBBB',
            userId: 'BBBBBB',
            deliveryEmployeeId: 'BBBBBB',
            status: 'BBBBBB',
            orderType: 'BBBBBB',
            isActive: true,
            shippingAddress: 'BBBBBB',
            paidTime: currentDate.format(DATE_TIME_FORMAT),
            completeTime: currentDate.format(DATE_TIME_FORMAT),
            shippmentTime: currentDate.format(DATE_TIME_FORMAT),
            deliveryTime: currentDate.format(DATE_TIME_FORMAT),
            cancelTime: currentDate.format(DATE_TIME_FORMAT),
            dispatchTime: currentDate.format(DATE_TIME_FORMAT),
            rejectTime: currentDate.format(DATE_TIME_FORMAT),
            acceptTime: currentDate.format(DATE_TIME_FORMAT),
            note: 'BBBBBB',
            rejectNote: 'BBBBBB',
            cancelNote: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            paidTime: currentDate,
            completeTime: currentDate,
            shippmentTime: currentDate,
            deliveryTime: currentDate,
            cancelTime: currentDate,
            dispatchTime: currentDate,
            rejectTime: currentDate,
            acceptTime: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Order', () => {
        service.delete('123').subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
