import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VcSearchComponent } from './vc-search.component';

describe('VcSearchComponent', () => {
  let component: VcSearchComponent;
  let fixture: ComponentFixture<VcSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VcSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VcSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
