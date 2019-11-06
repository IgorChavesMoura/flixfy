import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate, Router } from '@angular/router';

import { AuthService } from './auth.service';
import { UserProfile } from '../models/User';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {

    adminRoutes:string[] = ['categories','category','formats','format']

    constructor(private auth:AuthService, private router:Router) {

    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

        let routeSeg = route.url[0].path;

        if(this.adminRoutes.indexOf(routeSeg) >= 0 && this.auth.currentUser.profile == UserProfile.USER){

            return false;

        }

        return !!this.auth.currentUser;
    }
}