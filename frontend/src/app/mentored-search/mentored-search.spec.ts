import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MentoredSearch } from './mentored-search';

describe('MentoredSearch', () => {
  let component: MentoredSearch;
  let fixture: ComponentFixture<MentoredSearch>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MentoredSearch]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MentoredSearch);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
