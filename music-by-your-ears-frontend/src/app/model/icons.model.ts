export interface IBaseIcons{
  name: string;
  image: any;
  imageFile: string;
}

// tslint:disable-next-line:no-empty-interface
export interface ICreateIcon extends IBaseIcons {
}

export interface IIcon extends IBaseIcons {
  id: number;
}

export class CreateIcon implements ICreateIcon {
  constructor(public name: string,
              public image: any,
              public imageFile: string) {
  }
}

export class Icon implements IIcon {
  constructor(public id: number,
              public name: string,
              public image: any,
              public imageFile: string) {

  }
}
