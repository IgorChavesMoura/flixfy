import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VcListItemComponent } from './vc-list-item.component';

describe('VcListItemComponent', () => {
  let component: VcListItemComponent;
  let fixture: ComponentFixture<VcListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VcListItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VcListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
