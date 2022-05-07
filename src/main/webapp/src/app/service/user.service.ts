import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

  private readonly _usersUrl: string;
  private readonly _loginUrl: string;

  constructor(private http: HttpClient) {
    this._usersUrl = 'http://localhost:8082/api/v1/user';
    this._loginUrl = 'http://localhost:8082/api/v1/login';
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this._usersUrl);
  }

  public save(user: User) {
    return this.http.post<User>(this._usersUrl, user);
  }

  public login(user: User) {
    let formData: FormData = new FormData();
    formData.append("name", user.name);
    formData.append("password", user.password);
    return this.http.post(this._loginUrl, formData);
  }
}
