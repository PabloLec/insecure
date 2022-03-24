import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/user';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

  private usersUrl: string;
  private loginUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8082/api/v1/user';
    this.loginUrl = 'http://localhost:8082/api/v1/login';
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }

  public save(user: User) {
    return this.http.post<User>(this.usersUrl, user);
  }

  public login(user: User) {
    let formData: FormData = new FormData();
    formData.append("name", user.name);
    // @ts-ignore
    formData.append("password", user.password);
    return this.http.post(this.loginUrl, formData);
  }
}
