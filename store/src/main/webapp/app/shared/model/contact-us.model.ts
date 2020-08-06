export interface IContactUs {
  id?: string;
  name?: string;
  email?: string;
  phoneNumber?: string;
  message?: string;
}

export class ContactUs implements IContactUs {
  constructor(public id?: string, public name?: string, public email?: string, public phoneNumber?: string, public message?: string) {}
}
