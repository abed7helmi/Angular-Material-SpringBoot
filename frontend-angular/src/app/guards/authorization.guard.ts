import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  GuardResult,
  MaybeAsync,
  Router,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import { Observable } from 'rxjs';
import {AuthenticationService} from "../services/authentification.service";

@Injectable()
export  class  AuthorizationGuard  {
  constructor(private authService : AuthenticationService,
              private router : Router) {
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    let authorize = false;
    let authorizedRoles : string[] = route.data['roles'] ;
    let roles : string[] = this.authService.roles ;
    for (let i = 0; i < roles.length; i++) {
      if(authorizedRoles.includes(roles[i])){
        authorize = true;
      }
    }
    return authorize;
  }
}
