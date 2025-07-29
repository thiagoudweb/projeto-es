import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MentoredRegisterComponent } from './mentored-register.component';

describe('MentoredRegisterComponent', () => {
  let component: MentoredRegisterComponent;
  let fixture: ComponentFixture<MentoredRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MentoredRegisterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MentoredRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
