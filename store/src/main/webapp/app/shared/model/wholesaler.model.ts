export interface IWholesaler {
  id?: string;
  name?: string;
  userId?: string;
}

export class Wholesaler implements IWholesaler {
  constructor(public id?: string, public name?: string, public userId?: string) {}
}
