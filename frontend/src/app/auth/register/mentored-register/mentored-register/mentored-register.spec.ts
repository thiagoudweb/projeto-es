import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MentoredRegister } from './mentored-register';

describe('MentoredRegister', () => {
  let component: MentoredRegister;
  let fixture: ComponentFixture<MentoredRegister>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MentoredRegister]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MentoredRegister);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
