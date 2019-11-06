import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { VcService } from 'src/app/services/vc.service';
import { User, UserProfile } from 'src/app/models/User';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  users:User[];
  query:string;


  constructor(private auth:AuthService, private router:Router) { }

  ngOnInit() {

    this.findUsers();

  }

  findUsers(){

    this.auth.findUsers(this.query)
      .then(
        users => {
          this.users = users;
        }
      )

  }

  goToDetail(user){

    this.router.navigate([`/users/${user.id}`]);

  }

  deleteUser(user){

    this.auth.deleteUser(user.id)
      .then(
        () => {
          this.findUsers();
        }
      )

  }

  isAdmin(){

    return this.auth.currentUser.profile == UserProfile.ADMIN;

  }

}
