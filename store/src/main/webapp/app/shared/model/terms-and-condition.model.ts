export interface ITermsAndCondition {
  id?: string;
  note?: string;
  isActive?: boolean;
}

export class TermsAndCondition implements ITermsAndCondition {
  constructor(public id?: string, public note?: string, public isActive?: boolean) {
    this.isActive = this.isActive || false;
  }
}
