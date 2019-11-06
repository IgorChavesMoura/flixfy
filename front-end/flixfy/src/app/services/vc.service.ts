import { Injectable } from '@angular/core';
import { HttpClient, HttpEventType, HttpRequest, HttpResponse,HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { VideoContent } from '../models/VideoContent';

@Injectable({
  providedIn: 'root'
})
export class VcService {

  private apiUrl: string = environment.api_url;

  constructor(private auth:AuthService, private http:HttpClient) { }

  public saveVideoContent(id,title,duration,year,type,format,categories,picture,episodes){

    let url = `${this.apiUrl}/vc/save`;

    let formData = new FormData();

    if(!!id){

      formData.append('id',id);

    }

    formData.append('userId',this.auth.currentUser.id.toString());
    formData.append('title',title);
    formData.append('duration',duration);
    formData.append('year',year);
    formData.append('type',type);
    formData.append('format',format.id);
    
    categories.forEach(
      category => {

        formData.append('categories',category.id);

      }
    );

    if(!!picture){

      formData.append('picture',picture);

    }

    if(!!episodes && episodes.length > 0){

      episodes.forEach(
        episode => {

          formData.append('episodes',JSON.stringify(episode));

        }
      );

      if(episodes.length == 1){

        formData.append('episodes',JSON.stringify({}));

      }

    }

    let req = new HttpRequest('POST', url, formData);

    //console.log(formData);

    return this.http.request(req)
      // .subscribe(
      //   data => data
      //   ,
      //   error => {

      //     console.log('Erro no upload');

      //   }
      // )


  }

  public getVcPicture(vcId){

    return `${this.apiUrl}/vc/picture/${vcId}`;

  }

  public getVideoContent(id){

    let url = `${this.apiUrl}/vc/${id}`;

    return this.http.get(url,{ headers:new HttpHeaders(this.auth.headerLogged) }).toPromise()
      .then(
        vc => <VideoContent> vc
      );


  }

  public findVideoContents(query,categoryId?){

    let url = `${this.apiUrl}/vc`;

    if(!!query){

      url += `?query=${query}`;

      if(!!categoryId){

        url += `&category=${categoryId}`;
  
      }

    } else if(!!categoryId){

      url += `?category=${categoryId}`;

    }

 

    return this.http.get(url,{ headers:new HttpHeaders(this.auth.headerLogged) }).toPromise()
      .then(
        vcList => <VideoContent[]> vcList
      );

  }

  public getUserVideoContents(id?){

    let userId = !!id ? id : this.auth.currentUser.id;

    let url = `${this.apiUrl}/user/${userId}/vcs`;

    return this.http.get(url,{ headers:new HttpHeaders(this.auth.headerLogged) }).toPromise()
      .then(
        vcList => <VideoContent[]> vcList
      );

  }

  public deleteVideoContent(vcId){

    let url = `${this.apiUrl}/vc/${vcId}`;

    return this.http.delete(url,{ headers:new HttpHeaders(this.auth.headerLogged) }).toPromise();

  }

}
