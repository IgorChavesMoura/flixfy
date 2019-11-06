import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username:string ;
  password:string ;

  constructor(private router:Router, private auth:AuthService) { }

  ngOnInit() {
  
    //this.login();
  
  }

  goToSignUp(){

    this.router.navigate(['/signup']);


  }

  login(){

    this.auth.login(this.username,this.password).then(
      () => {

        this.router.navigate(['home']);

      }
    ).catch(
      error => {

        console.log("Peguei erro no component");

    

      }
    )

  }

}
