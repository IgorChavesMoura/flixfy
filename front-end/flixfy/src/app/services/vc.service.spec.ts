import { TestBed } from '@angular/core/testing';

import { VcService } from './vc.service';

describe('VcService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VcService = TestBed.get(VcService);
    expect(service).toBeTruthy();
  });
});
