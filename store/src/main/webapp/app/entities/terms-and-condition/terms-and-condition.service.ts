import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITermsAndCondition } from 'app/shared/model/terms-and-condition.model';

type EntityResponseType = HttpResponse<ITermsAndCondition>;
type EntityArrayResponseType = HttpResponse<ITermsAndCondition[]>;

@Injectable({ providedIn: 'root' })
export class TermsAndConditionService {
  public resourceUrl = SERVER_API_URL + 'api/terms-and-conditions';

  constructor(protected http: HttpClient) {}

  create(termsAndCondition: ITermsAndCondition): Observable<EntityResponseType> {
    return this.http.post<ITermsAndCondition>(this.resourceUrl, termsAndCondition, { observe: 'response' });
  }

  update(termsAndCondition: ITermsAndCondition): Observable<EntityResponseType> {
    return this.http.put<ITermsAndCondition>(this.resourceUrl, termsAndCondition, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ITermsAndCondition>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITermsAndCondition[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
