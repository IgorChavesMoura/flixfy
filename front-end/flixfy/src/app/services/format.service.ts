import { Injectable } from '@angular/core';
import { HttpClient, HttpEventType, HttpRequest, HttpResponse,HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { Format } from '../models/Format';
@Injectable({
  providedIn: 'root'
})
export class FormatService {

  private apiUrl: string = environment.api_url;

  constructor(private auth:AuthService, private http:HttpClient) { }

  public getAllFormats(){

    let url = `${this.apiUrl}/format`;

    return this.http.get(url,{ headers:new HttpHeaders(this.auth.headerLogged) }).toPromise()
      .then(
        formats => <Format[]> formats
      );

  }

  public getFormat(id){

    let url = `${this.apiUrl}/format/${id}`;

    return this.http.get(url,{ headers:new HttpHeaders(this.auth.headerLogged) }).toPromise()
      .then(
        format => <Format> format
      );

  }

  public saveFormat(format){
    
    let url = `${this.apiUrl}/admin/format`;

    let body = JSON.stringify(format);

    return this.http.post(url,body,{ headers:new HttpHeaders(this.auth.headerLogged) }).toPromise()
      

  }

  public deleteFormat(id){

    let url = `${this.apiUrl}/admin/format/${id}`;

    return this.http.delete(url,{ headers:new HttpHeaders(this.auth.headerLogged) }).toPromise()
      .then(
        data => data
      ).catch(error => {

        alert("Erro ao deletar formato");

      })

  }

}
