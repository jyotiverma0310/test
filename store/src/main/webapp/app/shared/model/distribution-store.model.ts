export interface IDistributionStore {
  id?: string;
  name?: string;
  userId?: string;
}

export class DistributionStore implements IDistributionStore {
  constructor(public id?: string, public name?: string, public userId?: string) {}
}
