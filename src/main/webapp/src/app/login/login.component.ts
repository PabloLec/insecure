import { Component } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from "../model/user";
import {UserService} from "../service/user.service";
import {UserCookieService} from "../service/user-cookie.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {

  public badCredentials = false
  user: User;
  isConnected: boolean;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userCookieService : UserCookieService,
    private userService: UserService) {
    this.user = new User();
  }

  onSubmit() {
    this.userService.login(this.user).subscribe(result => this.loginSuccessful(), error => this.badCredentials = true);
  }

  loginSuccessful() {
    this.userCookieService.user = this.user;
    this.userCookieService.isConnected = true;
    this.router.navigate(['/users']);
  }
}
