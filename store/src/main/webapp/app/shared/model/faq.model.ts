import { FaqType } from 'app/shared/model/enumerations/faq-type.model';

export interface IFaq {
  id?: string;
  type?: FaqType;
  question?: string;
  answer?: string;
  isActive?: boolean;
}

export class Faq implements IFaq {
  constructor(public id?: string, public type?: FaqType, public question?: string, public answer?: string, public isActive?: boolean) {
    this.isActive = this.isActive || false;
  }
}
