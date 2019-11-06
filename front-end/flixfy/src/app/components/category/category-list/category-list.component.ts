import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { Category } from 'src/app/models/Category';


@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent implements OnInit {

  categories:Category[];

  constructor(private categoryService:CategoryService, private router:Router) { }

  ngOnInit() {

    this.categoryService.getAllCategories()
      .then(
        (categories) => {

          this.categories = categories;

        }
      )

  }

  goToDetail(categoryId){

    this.router.navigate([`/category/${categoryId}`]);

  }

  goToCreate(){

    this.router.navigate([`/category/create`]);

  }

  deleteCategory(categoryId){

    this.categoryService.deleteCategory(categoryId)
      .then(
        () => {

          this.ngOnInit();

        }
      )

  }
}
