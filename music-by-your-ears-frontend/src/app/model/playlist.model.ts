import {IIcon} from './icons.model';
import {IAuthUser, IUser} from './user.model';

export interface IBasePlaylist {
  artist: string;
  name: string;
  nameToShow: string;
  coverImage: IIcon;
  audioFile: string;
  audio: any;
}

export interface ICreatePlaylist extends IBasePlaylist {
  userId: number;
}

export interface IPlaylist extends IBasePlaylist {
  id: number;
  user: IUser;
}

export class CreatePlaylist implements ICreatePlaylist {
  constructor(public name: string,
              public nameToShow: string,
              public artist: string,
              public coverImage: IIcon,
              public audioFile: string,
              public audio: any,
              public userId: number) {
  }
}

export class Playlist implements IPlaylist {
  constructor(public id: number,
              public name: string,
              public nameToShow: string,
              public artist: string,
              public coverImage: IIcon,
              public audioFile: string,
              public audio: any,
              public user: IUser) {
  }
}
