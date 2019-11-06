import { Component, OnInit, Input } from '@angular/core';
import { VcService } from 'src/app/services/vc.service';
import { VideoContent } from 'src/app/models/VideoContent';

@Component({
  selector: 'app-vc-list',
  templateUrl: './vc-list.component.html',
  styleUrls: ['./vc-list.component.scss']
})
export class VcListComponent implements OnInit {

  @Input()
  videoContentList:VideoContent[] = [];

  constructor() { }

  ngOnInit() {
  }

}
