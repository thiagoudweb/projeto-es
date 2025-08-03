import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import {
  AbstractControl, FormArray,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators
} from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../auth.service';
import { User } from '../../../../entity/user';
import { Mentor } from '../../../../entity/mentor';

@Component({
  selector: 'app-mentor-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './mentor-register.component.html',
  styleUrl: './mentor-register.component.css'
})
export class MentorRegisterComponent implements OnInit {
  title = 'Finalizar Cadastro';
  userData: User | null = null;

  private authService = inject(AuthService);
  private router = inject(Router);
  private fb = inject(FormBuilder);

  signupForm: FormGroup = this.fb.group({
    course: ['', [Validators.required, Validators.minLength(3)]],
    professionalSummary: ['', [Validators.required, Validators.minLength(3)]],
    interestArea: this.fb.array([], [Validators.required, MentorRegisterComponent.minSelectedCheckboxes(1)]),
    affiliationType: ['', [Validators.required]],
    specializations: ['', [Validators.required]],
  })

  static minSelectedCheckboxes(min: number) {
    return (control: AbstractControl) => {
      const formArray = control as FormArray;
      return formArray.length >= min ? null : { minSelected: true };
    };
  }

  get interestAreaArray(): FormArray {
    return this.signupForm.get('interestArea') as FormArray;
  }

  onInterestChange(event: any): void {
    const value = event.target.value;
    if (event.target.checked) {
      this.interestAreaArray.push(this.fb.control(value));
    } else {
      const index = this.interestAreaArray.controls.findIndex(x => x.value === value);
      if (index !== -1) {
        this.interestAreaArray.removeAt(index);
      }
    }
    this.interestAreaArray.markAsTouched();
    this.interestAreaArray.markAsDirty();
  }

  expandedSections: { [key: string]: boolean } = {};

  toggleSection(section: string): void {
    this.expandedSections[section] = !this.expandedSections[section];
  }


  ngOnInit() {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.userData = navigation.extras.state['userData'];
    }else if (history.state && history.state.userData) {
      this.userData = history.state.userData;
    }
    else{
      console.error('Nenhum dado recebido do cadastro inicial.');
    }
    if (!this.userData) {
      this.router.navigate(['/register']);
    }
  }

  onSubmit() {
    if (this.signupForm.valid && this.userData) {
      const mentorData: Mentor = {
        fullName: this.userData.fullName,
        cpf: this.userData.cpf,
        birthDate: this.userData.birthDate,
        course: this.signupForm.value.course,
        professionalSummary: this.signupForm.value.professionalSummary,
        affiliationType: this.signupForm.value.affiliationType,
        specializations: this.signupForm.value.specializations ? this.signupForm.value.specializations.split(',').map((s: string) => s.trim()): [],
        interestArea: this.interestAreaArray.value,
        email: this.userData.email,
        password: this.userData.password,
        role: 'MENTOR'
      };

      this.authService.registerMentor(mentorData).then(
          () => this.router.navigate(['/home']),
        )
        .catch(error => {
          console.error('Erro ao registrar Mentor:', error);
        });
    }
  }

}
