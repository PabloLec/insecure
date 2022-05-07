import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../service/user.service';
import {UserCookieService} from "../service/user-cookie.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: User[];

  constructor(private userService: UserService,
              private userCookieService : UserCookieService) {}

  ngOnInit() {
    this.userService.findAll().subscribe(data => {
      this.users = data;
    });
  }

  public isConnected(): boolean {
    return this.userCookieService.isConnected;
  }

  public isAdmin(): boolean {
    return this.userCookieService.isAdmin;
  }
}
