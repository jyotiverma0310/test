export interface IAboutUs {
  id?: string;
  note?: string;
  isActive?: boolean;
}

export class AboutUs implements IAboutUs {
  constructor(public id?: string, public note?: string, public isActive?: boolean) {
    this.isActive = this.isActive || false;
  }
}
