import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule, FormArray, AbstractControl } from '@angular/forms';
import { Mentor } from '../../entity/mentor';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-mentor-profile-modal',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule ],
  templateUrl: './edit-mentor-profile-modal.component.html',
  styleUrls: ['./edit-mentor-profile-modal.component.css']
})
export class EditMentorProfileModalComponent implements OnChanges {
  @Input() mentor!: Mentor;
  @Output() save = new EventEmitter<Mentor>();
  @Output() close = new EventEmitter<void>();

  mentorForm: FormGroup;
  expandedSections: { [key: string]: boolean } = {};

  static minSelectedCheckboxes(min: number) {
    return (control: AbstractControl) => {
      const formArray = control as FormArray;
      const totalSelected = formArray.controls.length;
      return totalSelected >= min ? null : { minSelected: true };
    };
  }

  constructor(private fb: FormBuilder) {
    this.mentorForm = this.fb.group({
      professionalSummary: ['', Validators.maxLength(1000)],
      course: ['', Validators.required],
      affiliationType: ['', Validators.required],
      specializations: ['', Validators.required],
      interestArea: this.fb.array([], [Validators.required, EditMentorProfileModalComponent.minSelectedCheckboxes(1)])
    });
  }

  get interestAreaArray(): FormArray {
    return this.mentorForm.get('interestArea') as FormArray;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['mentor'] && this.mentor) {
      const specializationsString = this.convertSpecializationsToString(this.mentor.specializations);

      this.mentorForm.patchValue({
        professionalSummary: this.mentor.professionalSummary || '',
        course: this.mentor.course || '',
        affiliationType: this.mentor.affiliationType || '',
        specializations: specializationsString
      });

      while (this.interestAreaArray.length !== 0) {
        this.interestAreaArray.removeAt(0);
      }

      if (this.mentor.interestArea) {
        const interests = Array.isArray(this.mentor.interestArea) 
          ? this.mentor.interestArea 
          : [this.mentor.interestArea];

        interests.forEach(interest => {
          this.interestAreaArray.push(this.fb.control(interest));
        });

        setTimeout(() => {
          interests.forEach(interest => {
            const checkbox = document.getElementById(this.getCheckboxId(interest)) as HTMLInputElement;
            if (checkbox) {
              checkbox.checked = true;
            }
          });
        });
      }

      this.mentorForm.markAsPristine();
      this.mentorForm.markAsUntouched();
    }
  }

  private convertSpecializationsToString(specializations: any): string {
    if (!specializations) {
      return '';
    }
    
    if (Array.isArray(specializations)) {
      return specializations.join(', ');
    }
    
    if (typeof specializations === 'string') {
      return specializations;
    }
    
    return '';
  }

  private convertSpecializationsToArray(specializations: string): string[] {
    if (!specializations || specializations.trim() === '') {
      return [];
    }
    
    return specializations
      .split(',')
      .map(spec => spec.trim())
      .filter(spec => spec.length > 0);
  }

  onInterestChange(event: any): void {
    const interestAreaArray = this.interestAreaArray;
    const value = event.target.value;

    if (event.target.checked) {
      interestAreaArray.push(this.fb.control(value));
    } else {
      const index = interestAreaArray.controls.findIndex(x => x.value === value);
      if (index !== -1) {
        interestAreaArray.removeAt(index);
      }
    }

    interestAreaArray.markAsTouched();
    interestAreaArray.markAsDirty();
  }

  private getCheckboxId(value: string): string {
    const idMap: { [key: string]: string } = {
      'TECNOLOGIA_DA_INFORMACAO': 'tech-info',
      'DESENVOLVIMENTO_DE_SOFTWARE': 'dev-software',
      'CIENCIA_DE_DADOS_E_IA': 'data-ia',
      'CIBERSEGURANCA': 'cybersec',
      'UX_UI_DESIGN': 'ux-ui',
      'ENGENHARIA_GERAL': 'eng-geral',
      'ENGENHARIA_CIVIL': 'eng-civil',
      'ENGENHARIA_DE_PRODUCAO': 'eng-prod',
      'MATEMATICA_E_ESTATISTICA': 'math-stat',
      'FISICA': 'fisica',
      'ADMINISTRACAO_E_GESTAO': 'admin-gestao',
      'EMPREENDEDORISMO_E_INOVACAO': 'empreend',
      'FINANCAS_E_CONTABILIDADE': 'financas',
      'RECURSOS_HUMANOS': 'rh',
      'LOGISTICA_E_CADEIA_DE_SUPRIMENTOS': 'logistica',
      'MARKETING_E_COMUNICACAO': 'marketing',
      'MARKETING_DIGITAL': 'marketing-digital',
      'JORNALISMO': 'jornalismo',
      'PUBLICIDADE_E_PROPAGANDA': 'publicidade',
      'COMUNICACAO_INSTITUCIONAL': 'com-institucional',
      'CIENCIAS_BIOLOGICAS_E_SAUDE': 'bio-saude',
      'MEDICINA': 'medicina',
      'PSICOLOGIA': 'psicologia',
      'NUTRICAO': 'nutricao',
      'BIOTECNOLOGIA': 'biotecnologia',
      'EDUCACAO': 'educacao',
      'CIENCIAS_HUMANAS_E_SOCIAIS': 'humanas',
      'LETRAS': 'letras',
      'HISTORIA': 'historia',
      'GEOGRAFIA': 'geografia',
      'SOCIOLOGIA': 'sociologia',
      'JURIDICO': 'juridico',
      'DIREITO_DIGITAL': 'direito-digital',
      'MEIO_AMBIENTE_E_SUSTENTABILIDADE': 'sustentabilidade'
    };
    return idMap[value] || '';
  }

  canSave(): boolean {
    return this.mentorForm.valid && (this.mentorForm.dirty || this.mentorForm.touched);
  }

  onSave() {
    if (this.mentorForm.valid) {
      const formData = this.mentorForm.value;
      
      const specializationsArray = this.convertSpecializationsToArray(formData.specializations);
      
      const updatedMentor: Mentor = {
        ...this.mentor,
        ...formData,
        specializations: specializationsArray, 
        interestArea: this.interestAreaArray.value 
      };
      
      this.save.emit(updatedMentor);
    }
  }

  onClose() {
    this.close.emit();
  }

  toggleSection(section: string): void {
    this.expandedSections[section] = !this.expandedSections[section];
  }
}