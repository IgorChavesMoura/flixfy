import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; 
import { Category } from 'src/app/models/Category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-category-detail',
  templateUrl: './category-detail.component.html',
  styleUrls: ['./category-detail.component.scss']
})
export class CategoryDetailComponent implements OnInit {

  category:Category;
  categoryBackup:Category;
  editing:boolean = false;

  constructor(private categoryService:CategoryService, private route:ActivatedRoute, private router:Router) { }

  ngOnInit() {

    this.route.params.subscribe(
      params => {

        if(!!params.id){

          this.categoryService.getCategory(params.id)
            .then(
              category => {

                this.category = category;
                console.log(this.category);

              }
            )

        } else {

          this.category = new Category();
          this.editing = true;

        }

      }
    );

  }

  edit(){

    this.categoryBackup = Object.assign({},this.category);
    this.editing = true;
  

  }

  cancel(){

    if(!this.category.id){

      this.router.navigate(['/categories']);
      return;

    }

    this.category = this.categoryBackup;
    this.categoryBackup = undefined;
    this.editing = false;


  }

  saveCategory(){

    this.categoryService.saveCategory(this.category)
      .then(
        () => {

          this.router.navigate(['/categories'])

        }
      )

  }

}
