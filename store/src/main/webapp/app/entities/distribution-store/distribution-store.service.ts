import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDistributionStore } from 'app/shared/model/distribution-store.model';

type EntityResponseType = HttpResponse<IDistributionStore>;
type EntityArrayResponseType = HttpResponse<IDistributionStore[]>;

@Injectable({ providedIn: 'root' })
export class DistributionStoreService {
  public resourceUrl = SERVER_API_URL + 'api/distribution-stores';

  constructor(protected http: HttpClient) {}

  create(distributionStore: IDistributionStore): Observable<EntityResponseType> {
    return this.http.post<IDistributionStore>(this.resourceUrl, distributionStore, { observe: 'response' });
  }

  update(distributionStore: IDistributionStore): Observable<EntityResponseType> {
    return this.http.put<IDistributionStore>(this.resourceUrl, distributionStore, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IDistributionStore>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDistributionStore[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
