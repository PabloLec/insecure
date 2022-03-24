import { Injectable } from '@angular/core';
import { CookieService } from "ngx-cookie-service";
import { User } from "../model/user";

@Injectable({providedIn: 'root'})
export class UserCookieService {

  public user: User
  public isConnected: boolean = false

  constructor(private cookieService: CookieService) {}

  public setUser(user: User) {
    this.user = user
    this.isConnected = true
  }

}
