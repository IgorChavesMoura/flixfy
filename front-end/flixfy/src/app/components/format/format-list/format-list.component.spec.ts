import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormatListComponent } from './format-list.component';

describe('FormatListComponent', () => {
  let component: FormatListComponent;
  let fixture: ComponentFixture<FormatListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormatListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormatListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
