import { Component } from '@angular/core';
import {User} from "../model/user";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  user: User;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService) {
    this.user = new User();
  }

  onSubmit() {
    this.userService.save(this.user).subscribe((result: any) => this.signupSuccessful(result));
  }

  signupSuccessful(result: any) {
    this.router.navigate(['/login'], { queryParams: { signupPassword: result }});
  }

}
