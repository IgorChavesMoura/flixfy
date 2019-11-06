import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { VideoContent, ContentType } from 'src/app/models/VideoContent';
import { VcService } from 'src/app/services/vc.service';
import { UserProfile } from 'src/app/models/User';
import { AuthService } from 'src/app/services/auth.service';
import { EventService } from 'src/app/services/event.service';


@Component({
  selector: 'app-vc-detail',
  templateUrl: './vc-detail.component.html',
  styleUrls: ['./vc-detail.component.scss']
})
export class VcDetailComponent implements OnInit {

  videoContent:VideoContent;

  constructor(private videoContentService:VcService, private auth:AuthService, private eventService:EventService, private router:Router, private route:ActivatedRoute, private location:Location) { }

  ngOnInit() {

    this.route.params.subscribe(
        params => {

          this.videoContentService.getVideoContent(params.id)
            .then(
              vc => {

                this.videoContent = vc;
                console.log(vc);

              }
            )

        }
    );

  }

  getVcPicture(){

    return this.videoContentService.getVcPicture(this.videoContent.id);

  }

  getVcType(){

    if(this.videoContent.type == ContentType.MOVIE){

      return 'Filme';

    } else if(this.videoContent.type == ContentType.TV_SERIES){

      return 'Série';

    }
  

  }

  getVcDuration(){

    return `${this.videoContent.duration} minutos`;

  }

  getVcEpisodes(){

    if(this.videoContent.episodes.length == 1){

      return `1 Episódio`;

    }

    return `${this.videoContent.episodes.length} episódios`;

  }

  getVcCategories(){

    let result = '';

    for(let i = 0; i < this.videoContent.categories.length; i++){

      let category = this.videoContent.categories[i];

      if(i == 0){

        result += category.description;

      } else {

        result += `, ${category.description}`;

      }


    }

    return result;

  }

  isMovie(){

    return this.videoContent.type == ContentType.MOVIE;

  }

  isTvSeries(){

    return this.videoContent.type == ContentType.TV_SERIES;

  }

  deleteVc(){

    this.videoContentService.deleteVideoContent(this.videoContent.id)
      .then(
        () => {

          this.location.back();
          this.eventService.vcDeletedEvent.emit();

        }
      );

  }
  

  canEdit(){

    return (this.auth.currentUser.profile == UserProfile.ADMIN) || (this.videoContent.owner.id == this.auth.currentUser.id);

  }

  goToEdit(){

    this.router.navigate([`/vc/${this.videoContent.id}/edit`]);

  }

}
