import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { VideoContent } from 'src/app/models/VideoContent';
import { VcService } from 'src/app/services/vc.service';
import { EventService } from 'src/app/services/event.service';
import { User } from 'src/app/models/User';
import { CategoryService } from 'src/app/services/category.service';
import { Category } from 'src/app/models/Category';

@Component({
  selector: 'app-vc-search',
  templateUrl: './vc-search.component.html',
  styleUrls: ['./vc-search.component.scss']
})
export class VcSearchComponent implements OnInit {

  query:string;

  categories:Category[];

  selectedCategory:Category;

  videoContentList:VideoContent[];

  constructor(private videoContentService:VcService, private categoryService:CategoryService, private router:Router) { }

  ngOnInit() {

    this.categoryService.getAllCategories()
      .then(
        categories => {

          this.categories = categories;

        }
      )

  }

  findContent(){


    let promise;

    if(!!this.query){


      if(!!this.selectedCategory){

        promise = this.videoContentService.findVideoContents(this.query,this.selectedCategory.id);

      } else {

        promise = this.videoContentService.findVideoContents(this.query);

      }

      promise.then(
        vcList => {

          this.videoContentList = vcList;

        }
      );


    } else {

      if(!!this.selectedCategory){

        promise = this.videoContentService.findVideoContents(undefined,this.selectedCategory.id);

        promise.then(
          vcList => {
  
            this.videoContentList = vcList;
  
          }
        );

      } else {

        this.videoContentList = [];

      }

      

    }

  }

}
