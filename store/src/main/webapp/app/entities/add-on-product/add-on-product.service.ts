import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAddOnProduct } from 'app/shared/model/add-on-product.model';

type EntityResponseType = HttpResponse<IAddOnProduct>;
type EntityArrayResponseType = HttpResponse<IAddOnProduct[]>;

@Injectable({ providedIn: 'root' })
export class AddOnProductService {
  public resourceUrl = SERVER_API_URL + 'api/add-on-products';

  constructor(protected http: HttpClient) {}

  create(addOnProduct: IAddOnProduct): Observable<EntityResponseType> {
    return this.http.post<IAddOnProduct>(this.resourceUrl, addOnProduct, { observe: 'response' });
  }

  update(addOnProduct: IAddOnProduct): Observable<EntityResponseType> {
    return this.http.put<IAddOnProduct>(this.resourceUrl, addOnProduct, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IAddOnProduct>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAddOnProduct[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
