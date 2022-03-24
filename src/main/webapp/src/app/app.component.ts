import { Component } from '@angular/core';
import { UserCookieService } from "./service/user-cookie.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "./service/user.service";
import {User} from "./model/user";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title: string = 'app';

  constructor(
    private userCookieService : UserCookieService
    ) {
  }

  public isConnected(): boolean {
    return this.userCookieService.isConnected;
  }


}
