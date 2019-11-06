import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VcDetailComponent } from './vc-detail.component';

describe('VcDetailComponent', () => {
  let component: VcDetailComponent;
  let fixture: ComponentFixture<VcDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VcDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VcDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
