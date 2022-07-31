import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, map } from 'rxjs';
import { ApiServices } from '../Service/api-services/api.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  name: any;
  email: any;
  password: any;
  dob: any;
  gender: any;
  type: any = 'user';
  constructor(private apiService: ApiServices,private router : Router) { }

  ngOnInit(): void {
  }

  register() {
    let data = {
      id : 0,
      name: this.name,
      email: this.email,
      dateOfBirth: this.dob,
      gender: this.gender,
      password: this.password
    }

    this.apiService.register(data,this.type).pipe(
      map(resp => {
        console.log(resp)
      }),
      catchError(err => {
        throw err;
      })
    )
      .subscribe(
        resp => console.log(resp),
        err => {
          if(err.status == 200){
            alert("registered successfully");
            this.router.navigate(['/login']);
          }
        }
      );

  }

}
