import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { VideoContent } from 'src/app/models/VideoContent';
import { VcService } from 'src/app/services/vc.service';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  videoContentList:VideoContent[] = []

  constructor(private auth:AuthService, private videoContentService:VcService, private eventService:EventService, private router:Router) { }

  ngOnInit() {

    this.videoContentService.getUserVideoContents()
      .then(
        vcList => {
          this.videoContentList = vcList;
        }
      );

      this.eventService.vcDeletedEvent.subscribe(
        () => {

          this.ngOnInit();

        }
      );

  }

  goToCreateVc(){

    this.router.navigate(['/vc/create']);

  }

}
