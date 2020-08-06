import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IContactUs } from 'app/shared/model/contact-us.model';

type EntityResponseType = HttpResponse<IContactUs>;
type EntityArrayResponseType = HttpResponse<IContactUs[]>;

@Injectable({ providedIn: 'root' })
export class ContactUsService {
  public resourceUrl = SERVER_API_URL + 'api/contactuses';

  constructor(protected http: HttpClient) {}

  create(contactUs: IContactUs): Observable<EntityResponseType> {
    return this.http.post<IContactUs>(this.resourceUrl, contactUs, { observe: 'response' });
  }

  update(contactUs: IContactUs): Observable<EntityResponseType> {
    return this.http.put<IContactUs>(this.resourceUrl, contactUs, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IContactUs>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IContactUs[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
