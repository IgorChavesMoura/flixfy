import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { VcService } from 'src/app/services/vc.service';
import { User, UserProfile } from 'src/app/models/User';


@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.scss']
})
export class UserEditComponent implements OnInit {

  user:User;

  passwordConfirm:string;
  passwordMatch:boolean = true;

  constructor(private auth:AuthService, private router:Router) { }

  ngOnInit() {

      this.user = Object.assign({},this.auth.currentUser);

  }


  save(){

    if(!!this.user.password && !this.passwordMatch){

      alert("Senhas não coincidem");
      return;
    
    }


    this.auth.updateUser(this.user)
      .then(
        () => {

          this.router.navigate(['/home']);

        }
      )

  }

  verifyPasswordMatch(){

    if((!!this.passwordConfirm && !!this.user.password) && this.passwordConfirm == this.user.password){

      this.passwordMatch = true;
      return;

    }

    this.passwordMatch = false;

  }

}
