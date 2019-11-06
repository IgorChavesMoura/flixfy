import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FormatService } from 'src/app/services/format.service';
import { Format } from 'src/app/models/Format';


@Component({
  selector: 'app-format-list',
  templateUrl: './format-list.component.html',
  styleUrls: ['./format-list.component.scss']
})
export class FormatListComponent implements OnInit {

  formats:Format[];

  constructor(private formatService:FormatService, private router:Router) { }

  ngOnInit() {

    this.formatService.getAllFormats()
      .then(
        formats => {
          this.formats = formats;
        }
      )

  }

  goToDetail(formatId){

    this.router.navigate([`/format/${formatId}`]);

  }

  goToCreate(){

    this.router.navigate(['/format/create']);

  }

  deleteFormat(formatId){

    this.formatService.deleteFormat(formatId) 
      .then(
        () => {

          this.router.navigate(['/formats'])

        }
      )

  }

}
