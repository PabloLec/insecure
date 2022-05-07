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
  badCredentials = false
  user: User;
  isConnected: boolean;
  signupPassword: string | null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userCookieService : UserCookieService,
    private userService: UserService) {
    this.user = new User();
    this.route.queryParams.subscribe(params => {
      console.log("params")
      console.log(params)
      this.signupPassword = params['signupPassword'];
    });
    this.route.queryParamMap.subscribe(params => {
      this.signupPassword = params.get('signupPassword');
    });
  }

  onSubmit() {
    this.userService.login(this.user).subscribe((result: any) => this.loginSuccessful(result), error => this.badCredentials = true);
  }

  loginSuccessful(result: any) {
    console.log(result);
    this.userCookieService.setUser(result as User);
    this.router.navigate(['/users']);
  }
}
