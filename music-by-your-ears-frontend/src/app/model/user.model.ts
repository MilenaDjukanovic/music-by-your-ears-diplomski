import {Icon} from './icons.model';

// tslint:disable-next-line:no-empty-interface
export interface IBaseUser {

}

export interface IAuthUser extends IBaseUser {
  username: string;
  password: string;
}

export interface ICreateUser extends  IBaseUser {
  username: string;
  password: string;
  rePassword: string;
  firstName: string;
  lastName: string;
  about: string;
  profileImage?: Icon;
}

export interface IUpdateUser extends  IBaseUser {
  firstName?: string;
  lastName?: string;
  about?: string;
  profileImage?: Icon;
}

export interface IUser extends IBaseUser {
    id: number;
}

export class User implements IUser{
  constructor(
    public id: number,
    public username: string,
    public password: string
  ) {}
}

export class AuthUser implements IAuthUser {
  constructor(
    public username: string,
    public password: string
  ){}
}

export class CreateUser implements ICreateUser {
  constructor(
    public username: string,
    public password: string,
    public rePassword: string,
    public firstName: string,
    public lastName: string,
    public about: string,
    public profileImage?: Icon
  ){}
}

export class UpdateUser implements IUpdateUser {
  constructor(
    public firstName?: string,
    public lastName?: string,
    public about?: string,
    public profileImage?: Icon
  ){}
}
