import { ProductType } from 'app/shared/model/enumerations/product-type.model';
import { ProductSize } from 'app/shared/model/enumerations/product-size.model';

export interface IProduct {
  id?: string;
  name?: string;
  productType?: ProductType;
  productCategoryId?: string;
  price?: number;
  isActive?: boolean;
  productSize?: ProductSize;
}

export class Product implements IProduct {
  constructor(
    public id?: string,
    public name?: string,
    public productType?: ProductType,
    public productCategoryId?: string,
    public price?: number,
    public isActive?: boolean,
    public productSize?: ProductSize
  ) {
    this.isActive = this.isActive || false;
  }
}
