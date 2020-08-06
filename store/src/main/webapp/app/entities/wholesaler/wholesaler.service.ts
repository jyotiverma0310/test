import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWholesaler } from 'app/shared/model/wholesaler.model';

type EntityResponseType = HttpResponse<IWholesaler>;
type EntityArrayResponseType = HttpResponse<IWholesaler[]>;

@Injectable({ providedIn: 'root' })
export class WholesalerService {
  public resourceUrl = SERVER_API_URL + 'api/wholesalers';

  constructor(protected http: HttpClient) {}

  create(wholesaler: IWholesaler): Observable<EntityResponseType> {
    return this.http.post<IWholesaler>(this.resourceUrl, wholesaler, { observe: 'response' });
  }

  update(wholesaler: IWholesaler): Observable<EntityResponseType> {
    return this.http.put<IWholesaler>(this.resourceUrl, wholesaler, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IWholesaler>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWholesaler[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
