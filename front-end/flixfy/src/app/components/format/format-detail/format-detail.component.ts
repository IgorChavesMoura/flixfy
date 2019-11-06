import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; 
import { Format } from 'src/app/models/Format';
import { FormatService } from 'src/app/services/format.service';

@Component({
  selector: 'app-format-detail',
  templateUrl: './format-detail.component.html',
  styleUrls: ['./format-detail.component.scss']
})
export class FormatDetailComponent implements OnInit {

  format:Format;
  formatBackup:Format;

  editing:boolean = false;

  constructor(private formatService:FormatService, private route:ActivatedRoute, private router:Router) { }

  ngOnInit() {
  
    this.route.params.subscribe(
      params => {

        if(!!params.id){

          this.formatService.getFormat(params.id)
            .then(
              format => {
                this.format = format;
              }
            )

          

        } else {

          this.format = new Format();
          this.editing = true;

        }

      }
    );
  
  }

  edit(){

    this.formatBackup = Object.assign({},this.format);
    this.editing = true;
  

  }

  cancel(){

    if(!this.format.id){

      this.router.navigate(['/formats']);
      return;

    }

    this.format = this.formatBackup;
    this.formatBackup = undefined;
    this.editing = false;


  }

  saveFormat(){

    this.formatService.saveFormat(this.format)
      .then(
        () => {

          this.router.navigate(['formats']);

        }
      )

  }

}
