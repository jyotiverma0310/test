export interface IAddOnProduct {
  id?: string;
  name?: string;
  isActive?: boolean;
  price?: number;
  productCategoryId?: string;
}

export class AddOnProduct implements IAddOnProduct {
  constructor(
    public id?: string,
    public name?: string,
    public isActive?: boolean,
    public price?: number,
    public productCategoryId?: string
  ) {
    this.isActive = this.isActive || false;
  }
}
