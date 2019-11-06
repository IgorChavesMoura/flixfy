import { Injectable } from '@angular/core';
import { HttpClient, HttpEventType, HttpRequest, HttpResponse,HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { Category } from '../models/Category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private apiUrl: string = environment.api_url;

  constructor(private auth:AuthService, private http:HttpClient) { }


  public getAllCategories(){

    let url = `${this.apiUrl}/category`;

    return this.http.get(url,{ headers:new HttpHeaders(this.auth.headerLogged) }).toPromise()
      .then(
        categories => <Category[]> categories
      );

  }

  public getCategory(id){

    let url = `${this.apiUrl}/category/${id}`;

    return this.http.get(url,{ headers:new HttpHeaders(this.auth.headerLogged) }).toPromise()
      .then(
        category => <Category> category
      );

  }

  public saveCategory(category){
    
    let url = `${this.apiUrl}/admin/category`;

    let body = JSON.stringify(category);

    return this.http.post(url,body,{ headers:new HttpHeaders(this.auth.headerLogged) }).toPromise()
      

  }

  public deleteCategory(id){
    
    let url = `${this.apiUrl}/admin/category/${id}`;

    return this.http.delete(url,{ headers:new HttpHeaders(this.auth.headerLogged) }).toPromise()
      .then(
        data => data
      ).catch(
        error => {

          alert("Erro ao deletar categoria");

        }
      )
    

  }


}
