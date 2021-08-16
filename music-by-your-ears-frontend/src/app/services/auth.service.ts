import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {AuthUser, CreateUser, IBaseUser, ICreateUser, IUser, UpdateUser} from '../model/user.model';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly BASE_URL = 'spring/api/auth';

  private readonly API_REGISTER = '/register/user';
  private readonly API_LOGIN = '/login';
  private readonly API_GET_LOGGED_IN_USER = '/user';
  private readonly API_UPDATE_USER = '/update/user';
  private readonly API_UPDATE_USER_IMAGE = '/update/user/image';


  private readonly LOCAL_STORAGE_USER: string = 'currentUser';
  private readonly LOCAL_STORAGE_USER_TOKEN: string = 'userToken';

  private currentUserSubject!: BehaviorSubject<any>;
  private currentUser!: Observable<any>;

  constructor(private httpClient: HttpClient) {
    let user = localStorage.getItem(this.LOCAL_STORAGE_USER);
    user = user ? JSON.parse(user) : null;
    this.currentUserSubject = new BehaviorSubject<any>(user);
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public isAuthenticated(): boolean {
    return !!localStorage.getItem(this.LOCAL_STORAGE_USER);
  }

  public getCurrentUserValue(): any {
    return this.currentUserSubject.value;
  }

  public getUserToken(): any {
    if (localStorage.getItem(this.LOCAL_STORAGE_USER_TOKEN)) {
      return JSON.parse(localStorage.getItem(this.LOCAL_STORAGE_USER_TOKEN) as string).token;
    }

    return null;
  }

  public register(createUser: CreateUser): Observable<IUser> {
    const url = this.BASE_URL + this.API_REGISTER;
    return this.httpClient.post<IUser>(url, createUser);
  }

  public login(authUser: AuthUser): Observable<IUser> {
    const url = this.BASE_URL + this.API_LOGIN;

    return this.httpClient.post<IUser>(url, authUser, {observe: 'response'}).pipe(
      map(response => {
        localStorage.setItem(this.LOCAL_STORAGE_USER, JSON.stringify(response.body));
        localStorage.setItem(this.LOCAL_STORAGE_USER_TOKEN, JSON.stringify({token: response.headers.get('authorization')}));
        this.currentUserSubject.next(response.body);
        return response.body as IUser;
      })
    );
  }

  public getLoggedInUser(): Observable<ICreateUser> {
    const url = this.BASE_URL + this.API_GET_LOGGED_IN_USER;
    return this.httpClient.get<ICreateUser>(url);
  }

  public updateUser(userForUpdate: UpdateUser): Observable<ICreateUser> {
    const url = this.BASE_URL + this.API_UPDATE_USER;
    return this.httpClient.post<ICreateUser>(url, userForUpdate);
  }

  public updateUserImage(image: FormData): Observable<ICreateUser> {
    const url = this.BASE_URL + this.API_UPDATE_USER_IMAGE;
    return this.httpClient.post<ICreateUser>(url, image);
  }

  public logout(): void {
    localStorage.removeItem(this.LOCAL_STORAGE_USER);
    localStorage.removeItem(this.LOCAL_STORAGE_USER_TOKEN);
    this.currentUserSubject.next(null);
  }
}
