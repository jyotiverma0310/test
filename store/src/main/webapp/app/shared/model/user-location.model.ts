export interface IUserLocation {
  id?: string;
  userId?: string;
  latitude?: string;
  longitude?: string;
}

export class UserLocation implements IUserLocation {
  constructor(public id?: string, public userId?: string, public latitude?: string, public longitude?: string) {}
}
