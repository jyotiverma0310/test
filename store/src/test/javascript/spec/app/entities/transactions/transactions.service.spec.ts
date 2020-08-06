import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TransactionsService } from 'app/entities/transactions/transactions.service';
import { ITransactions, Transactions } from 'app/shared/model/transactions.model';
import { TransactionType } from 'app/shared/model/enumerations/transaction-type.model';
import { TransactionUser } from 'app/shared/model/enumerations/transaction-user.model';

describe('Service Tests', () => {
  describe('Transactions Service', () => {
    let injector: TestBed;
    let service: TransactionsService;
    let httpMock: HttpTestingController;
    let elemDefault: ITransactions;
    let expectedResult: ITransactions | ITransactions[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TransactionsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Transactions('ID', 'AAAAAAA', 0, TransactionType.Credit, TransactionUser.Employee, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Transactions', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Transactions()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Transactions', () => {
        const returnedFromService = Object.assign(
          {
            orderId: 'BBBBBB',
            amount: 1,
            transactionType: 'BBBBBB',
            transactionUser: 'BBBBBB',
            userId: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Transactions', () => {
        const returnedFromService = Object.assign(
          {
            orderId: 'BBBBBB',
            amount: 1,
            transactionType: 'BBBBBB',
            transactionUser: 'BBBBBB',
            userId: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Transactions', () => {
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
