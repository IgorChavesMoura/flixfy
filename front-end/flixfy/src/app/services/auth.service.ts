import { Injectable } from '@angular/core';
import { HttpClient, HttpEventType, HttpRequest, HttpResponse,HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { User } from '../models/User';
import { throws } from 'assert';

// import 'rxjs/add/operator/map';
// import 'rxjs/add/operator/toPromise';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl: string = environment.api_url;

  public headerNotLogged = {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  }

  public currentUser: User;

  public headerLogged;

  public headerFile;


  public authToken;


  constructor(private http:HttpClient) { }

  public verifyLogin(login){

    let url = `${this.apiUrl}/user-exists?login=${login}`;

    return this.http.get(url).toPromise()
      .then(
        (userExists:any) => {
          return userExists.exists;
        }
      )

  }

  public login(username,password){

    let url = `${this.apiUrl}/login`;

    let body = {

      username,
      password

    };

    return this.http.post(url,body,{ headers:new HttpHeaders(this.headerNotLogged) }).toPromise()
      .then(
        (data:any) => {
          
          this.currentUser = data.user;
          this.authToken = data.token;
          this.headerLogged = {

            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': 'FLIXFY ' + this.authToken,

          }
          this.headerFile = {
            'Content-Type': 'multipart/form-data',
            'Accept': undefined,
            'Authorization': 'FLIXFY ' + this.authToken,
          }

        }
      ).catch(error => {
        
        //console.log('Peguei o erro no service');
        alert('Usuário ou senha incorretos!');

        return error;
      
      });


  }

  public logOut(){

    let url = `${this.apiUrl}/logout`;


    return this.http.post(url,'',{ headers:new HttpHeaders(this.headerLogged) }).toPromise()
      .then(
        () => {

          this.headerLogged = undefined;
          this.currentUser = undefined;
          this.authToken = undefined;

        }
      )

  }

  public signUp(user){

    let url = `${this.apiUrl}/signup`;

    let body = JSON.stringify(user);

    return this.http.post(url,body, { headers:new HttpHeaders(this.headerNotLogged) }).toPromise()
      .then(
        data => data
      );


  }

  public getUser(id){

    let url = `${this.apiUrl}/user/${id}`;

    return this.http.get(url, { headers:new HttpHeaders(this.headerLogged) }).toPromise()
      .then(
        user => <User> user
      );


  } 

  public findUsers(query?){

    let url = `${this.apiUrl}/user/`;

    if(!!query){

      url += `?query=${query}`;

    }
  
    return this.http.get(url, { headers:new HttpHeaders(this.headerLogged) }).toPromise()
      .then(
        users => <User[]> users
      );


  }

  public updateUser(user){

    let url = `${this.apiUrl}/user/`;

    let body = JSON.stringify(user);

    return this.http.post(url,body, { headers:new HttpHeaders(this.headerLogged) }).toPromise()
      .then(
        user => {

          this.currentUser = <User> user;

        }
      )

  }

  public deleteUser(id){

    let url = `${this.apiUrl}/admin/user/${id}`;

    return this.http.delete(url, { headers:new HttpHeaders(this.headerLogged) }).toPromise()
    

  }

  public deleteCurrentUser(){

    let url = `${this.apiUrl}/user`;

    return this.http.delete(url, { headers:new HttpHeaders(this.headerLogged) }).toPromise()
      .then(
        () => this.logOut()
      )

  }

}
