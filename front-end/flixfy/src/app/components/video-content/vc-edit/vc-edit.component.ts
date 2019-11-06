import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { CategoryService } from 'src/app/services/category.service';
import { FormatService } from 'src/app/services/format.service';
import { VcService } from 'src/app/services/vc.service';
import { Category } from 'src/app/models/Category';
import { Format } from 'src/app/models/Format';
import { VideoContent, ContentType, Episode } from 'src/app/models/VideoContent';

@Component({
  selector: 'app-vc-edit',
  templateUrl: './vc-edit.component.html',
  styleUrls: ['./vc-edit.component.scss']
})
export class VcEditComponent implements OnInit {

  formats: Format[];
  categories: Category[];

  categoriesSelect: Array<{ category: Category, selected: boolean }>;

  videoContent: VideoContent;

  episodes: Episode[] = [];

  picture: File = null;

  constructor(private categoryService: CategoryService, private formatService: FormatService, private vcService: VcService, private router:Router, private route: ActivatedRoute, private location: Location) { }

  ngOnInit() {

    let promises = [

      this.categoryService.getAllCategories().then(

        categories => {

          this.categories = categories;
          this.buildCategoriesSelect();

        }

      ),
      this.formatService.getAllFormats().then(

        formats => {

          this.formats = formats;

        }

      )

    ]

    Promise.all(promises)
      .then(
        () => {


          this.route.params.subscribe(

            params => {

              if (!!params.id) {

                this.vcService.getVideoContent(params.id)
                  .then(
                    vc => {

                      this.videoContent = vc;
                      this.episodes = [].concat(this.videoContent.episodes);
                      this.formats.forEach(
                        format => {

                          if (format.id == this.videoContent.format.id) {

                            this.videoContent.format = format;

                          }

                        }
                      )
                      this.buildCategoriesSelect();

                    }
                  )

              } else {

                this.videoContent = new VideoContent();

              }

            }

          );

        }
      )


  }

  buildCategoriesSelect() {

    this.categoriesSelect = [];

    if (!!this.videoContent && !!this.videoContent.categories) {


      this.categories.forEach(
        category => {

          let selected = false;

          this.videoContent.categories.forEach(

            vcCategory => {

              if (category.id == vcCategory.id) {

                selected = true;

              }

            }

          );

          this.categoriesSelect.push({ category: category, selected: selected });
        }
      );


    } else {

      this.categories.forEach(
        category => {
          this.categoriesSelect.push({ category: category, selected: false });
        }
      );

    }

  }

  saveVc() {

    if (!this.hasACategorySelected()) {

      alert("Selecione pelo menos uma categoria");
      return;

    }

    if (this.videoContent.type == ContentType.TV_SERIES) {

      this.videoContent.episodes = this.episodes.filter(e => !!e.title);

    }

    this.processCategoriesSelect();



    this.vcService.saveVideoContent(this.videoContent.id, this.videoContent.title, this.videoContent.duration, this.videoContent.year, this.videoContent.type, this.videoContent.format, this.videoContent.categories, this.picture, this.videoContent.episodes)
      .subscribe(
        data => {

          //this.location.back();
          
          if(!!this.videoContent.id){

            this.router.navigate([`/vc/${this.videoContent.id}/detail`]);


          } else {

            this.router.navigate([`/home`]);

          }

        },
        error => {

          console.log('Erro ao salvar conteúdo');

        }
      );
  }

  processCategoriesSelect() {

    this.videoContent.categories = []

    this.categoriesSelect.forEach(
      categorySelect => {

        if (categorySelect.selected) {

          this.videoContent.categories.push(categorySelect.category);

        }

      }
    );

  }

  hasACategorySelected() {

    let selected = false;

    this.categoriesSelect.forEach(
      categorySelect => {

        if (categorySelect.selected) {

          selected = true;

        }

      }
    );

    return selected;

  }

  onFormatChange() {

    //console.log(this.videoContent);

  }

  onTypeChange(type) {

    if (type == 'MOVIE') {

      this.videoContent.type = ContentType.MOVIE;
      //this.episodes = [];

    } else if (type == 'TV_SERIES') {

      this.videoContent.type = ContentType.TV_SERIES;


    }





  }

  isTVSeries() {

    return this.videoContent.type == ContentType.TV_SERIES;

  }

  addEpisode() {

    this.episodes.push(new Episode());

  }

  deleteEpisode(episode) {

    this.episodes = this.episodes.filter(e => e != episode);


  }

  handleFileInput(files: FileList) {
    this.picture = files.item(0);
  }

}
