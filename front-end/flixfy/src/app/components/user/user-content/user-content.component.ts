import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { VideoContent } from 'src/app/models/VideoContent';
import { VcService } from 'src/app/services/vc.service';
import { EventService } from 'src/app/services/event.service';
import { User } from 'src/app/models/User';

@Component({
  selector: 'app-user-content',
  templateUrl: './user-content.component.html',
  styleUrls: ['./user-content.component.scss']
})
export class UserContentComponent implements OnInit {

  user:User;
  videoContentList:VideoContent[];

  constructor(private auth:AuthService, private videoContentService:VcService, private eventService:EventService, private router:Router, private route:ActivatedRoute) { }

  ngOnInit() {

    this.route.params.subscribe(
      params => {

        this.auth.getUser(params.id)
          .then(
            user => {
              this.user = user;
              console.log(user);
              this.videoContentService.getUserVideoContents(params.id)
                .then(
                  vcs => {

                    this.videoContentList = vcs;

                  }
                )

            }
          )

      }
    );

  }

}
