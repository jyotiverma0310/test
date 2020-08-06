export interface IProductCategory {
  id?: string;
  name?: string;
  isActive?: boolean;
}

export class ProductCategory implements IProductCategory {
  constructor(public id?: string, public name?: string, public isActive?: boolean) {
    this.isActive = this.isActive || false;
  }
}
