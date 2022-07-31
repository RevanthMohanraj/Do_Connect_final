import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  currentUser : any = localStorage.getItem("loggedinUser");
  currentUserType : any = localStorage.getItem("loggedInUserType");
  
  constructor(private router : Router) { }

  ngOnInit(): void {
  }

  isLoggedIn(){
    let user = localStorage.getItem('loggedinUser');
    if(user && user != ''){
      return true;
    }else{
      return false;
    }
  }

  logOut(){
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
