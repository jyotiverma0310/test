export interface IProductSubCategory {
  id?: string;
  name?: string;
  isActive?: boolean;
}

export class ProductSubCategory implements IProductSubCategory {
  constructor(public id?: string, public name?: string, public isActive?: boolean) {
    this.isActive = this.isActive || false;
  }
}
