import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserProfile } from 'src/app/models/User';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  constructor(private auth:AuthService, private router:Router) { }

  ngOnInit() {
  }

  isAdmin(){

    return this.auth.currentUser.profile == UserProfile.ADMIN;

  }

  logout(){

    this.auth.logOut()
      .then(
        () => {
          this.router.navigate(['/login']);
        }
      )

  }

  deleteUser(){

    this.auth.deleteCurrentUser()
      .then(
        () => {

          this.router.navigate(['/login']);

        }
      )

  }

}
