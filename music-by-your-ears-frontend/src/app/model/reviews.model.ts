import {IPlaylist} from './playlist.model';
import {IUser} from './user.model';

export interface IBaseReview {
  comment: string;
}

export interface ICreateReview extends IBaseReview{
  playlistId: number;
  userId: number;
}

export interface IReview extends IBaseReview {
  id: number;
  playlist: IPlaylist;
  user: IUser;
}

export class CreateReview implements ICreateReview {
  constructor(public comment: string,
              public playlistId: number,
              public userId: number) {
  }
}

export class Review implements IReview {
  constructor(public id: number,
              public comment: string,
              public playlist: IPlaylist,
              public user: IUser) {
  }
}

