import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { User } from 'src/app/models/User';

@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.scss']
})
export class SingupComponent implements OnInit {

  user:User = new User();

  passwordConfirm:string;

  loginAlreadyExists:boolean = false;
  passwordMatch:boolean = true;

  constructor(private auth:AuthService, private router:Router) { }

  ngOnInit() {
  }

  createUser(){

    if(!this.passwordMatch && this.loginAlreadyExists){

      alert('Há erros no formulário!');

    }

    this.auth.signUp(this.user).then(
      () => {
        alert('Usuário criado com sucesso!');
        this.router.navigate(['/login']);
      }
    );

  }

  verifyNewLogin(){

    console.log('Verifying')

    this.auth.verifyLogin(this.user.login).then(
      exists => {

        this.loginAlreadyExists = exists;

      }
    );

  }

  verifyPasswordMatch(){

    if((!!this.passwordConfirm && !!this.user.password) && this.passwordConfirm == this.user.password){

      this.passwordMatch = true;
      return;

    }

    this.passwordMatch = false;

  }

}
