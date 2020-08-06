import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserLocation } from 'app/shared/model/user-location.model';

type EntityResponseType = HttpResponse<IUserLocation>;
type EntityArrayResponseType = HttpResponse<IUserLocation[]>;

@Injectable({ providedIn: 'root' })
export class UserLocationService {
  public resourceUrl = SERVER_API_URL + 'api/user-locations';

  constructor(protected http: HttpClient) {}

  create(userLocation: IUserLocation): Observable<EntityResponseType> {
    return this.http.post<IUserLocation>(this.resourceUrl, userLocation, { observe: 'response' });
  }

  update(userLocation: IUserLocation): Observable<EntityResponseType> {
    return this.http.put<IUserLocation>(this.resourceUrl, userLocation, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IUserLocation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserLocation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
