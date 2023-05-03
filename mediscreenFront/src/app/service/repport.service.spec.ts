import { TestBed } from '@angular/core/testing';

import { RepportService } from './repport.service';

describe('RepportService', () => {
  let service: RepportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RepportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
