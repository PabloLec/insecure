import { Injectable } from '@angular/core';
import { CookieService } from "ngx-cookie-service";
import { User } from "../model/user";

@Injectable({providedIn: 'root'})
export class UserCookieService {
  private _user: User
  private _isConnected: boolean = false
  private _isAdmin: boolean = false
  private cookieService: CookieService;

  constructor(cookieService: CookieService) {
    this.cookieService = cookieService;
  }

  get user(): User {
    return this._user;
  }

  get isConnected(): boolean {
    return this.cookieService.get('isConnected') == "true";
  }

  get isAdmin(): boolean {
    return this.cookieService.get('isAdmin') == "true";
  }

  public setUser(user: User) {
    console.log("UserCookieService received: " + user)
    this._user = user
    this._isConnected = true
    this._isAdmin = user.isAdmin;
    this.updateCookies()
  }

  private updateCookies() {
    this.cookieService.set("isConnected", String(this._isConnected))
    this.cookieService.set("isAdmin", String(this._isAdmin))
  }

}
