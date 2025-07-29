import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MentorRegisterComponent } from './mentor-register.component';

describe('MentorRegisterComponent', () => {
  let component: MentorRegisterComponent;
  let fixture: ComponentFixture<MentorRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MentorRegisterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MentorRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
