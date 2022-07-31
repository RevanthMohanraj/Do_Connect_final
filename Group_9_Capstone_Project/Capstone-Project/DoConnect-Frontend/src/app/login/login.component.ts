import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, map } from 'rxjs';
import { ApiServices } from '../Service/api-services/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = true;
  admin = false;
  email: any;
  password: any;

  type: any = 'user';
  constructor(private apiService: ApiServices,private router : Router) { }

  ngOnInit(): void {
  }

  loginDetails() {
    this.user = !this.user;
    this.admin = !this.admin;
  }

  login(){
    let data={
      email: this.email,
      password: this.password
    }
    this.apiService.login(data,this.type)
      .subscribe(
        resp => console.log(resp),
        err => {
          if (err.error.text == 'Login Success') {
            alert("LoggedIn Successfully");
            localStorage.setItem('loggedinUser',this.email);
            localStorage.setItem('loggedInUserType',this.type);
            this.router.navigate(['/Questions']);

          } else {
            alert(err.error.text);
          }
        }
      );
    
  }


}
