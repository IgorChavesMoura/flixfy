import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { VideoContent, ContentType } from 'src/app/models/VideoContent';
import { VcService } from 'src/app/services/vc.service';
import { AuthService } from 'src/app/services/auth.service';
import { EventService } from 'src/app/services/event.service';
import { UserProfile } from 'src/app/models/User';

@Component({
  selector: 'app-vc-list-item',
  templateUrl: './vc-list-item.component.html',
  styleUrls: ['./vc-list-item.component.scss']
})
export class VcListItemComponent implements OnInit {

  @Input()
  videoContent:VideoContent;

  constructor(private auth:AuthService, private videoContentService:VcService, private router:Router, private eventService:EventService) { }

  ngOnInit() {
  }

  goToDetail(){

    this.router.navigate([`/vc/${this.videoContent.id}/detail`]);

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

          this.eventService.vcDeletedEvent.emit();

        }
      );

  }

  canDelete(){

    return (this.auth.currentUser.profile == UserProfile.ADMIN) || (this.videoContent.owner.id == this.auth.currentUser.id);

  }

}
