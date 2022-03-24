import { Component } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from "../model/user";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public badCredentials = false
  user: User;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService) {
    this.user = new User();
  }

  onSubmit() {
    this.userService.login(this.user).subscribe(result => this.gotoUserList(), error => this.badCredentials = true);
  }

  gotoUserList() {
    this.router.navigate(['/users']);
  }
}
