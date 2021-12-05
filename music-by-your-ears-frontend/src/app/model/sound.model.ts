import {IUser} from './user.model';
import {ICreateIcon, IIcon} from './icons.model';

export interface IBaseSound {
  nameToShow: string;
  name: string;
  audioFile: string;
  audio: any;
  soundPublic: boolean;
}

export interface ICreateSound extends IBaseSound {
  userId: number;
  icon: ICreateIcon;
}

export interface ISound extends IBaseSound {
  id: number;
  icon: IIcon;
  user: IUser;
}

export class CreateSound implements ICreateSound {
  constructor(public  nameToShow: string,
              public name: string,
              public audio: any,
              public audioFile: string,
              public soundPublic: boolean,
              public icon: ICreateIcon,
              public userId: number) {
  }
}

export class Sound implements ISound {
  constructor(public id: number,
              public  nameToShow: string,
              public name: string,
              public audio: any,
              public audioFile: string,
              public soundPublic: boolean,
              public icon: IIcon,
              public user: IUser) {
  }
}
