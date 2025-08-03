import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  Output,
  SimpleChanges
} from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormArray,
  FormsModule,
  ReactiveFormsModule,
  Validators,
  AbstractControl
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Mentored } from '../../entity/mentored';

@Component({
  selector: 'app-edit-mentored-modal',
  standalone: true,
  templateUrl: './edit-mentored-modal.component.html',
  styleUrls: ['./edit-mentored-modal.component.css'],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class EditMentoredModalComponent implements OnChanges {
  @Input() mentored!: Mentored;
  @Output() save = new EventEmitter<Mentored>();
  @Output() close = new EventEmitter<void>();

  mentoredForm: FormGroup;
  expandedSections: { [key: string]: boolean } = {};

  static minSelectedCheckboxes(min: number) {
    return (control: AbstractControl) => {
      const formArray = control as FormArray;
      const totalSelected = formArray.controls.length;
      return totalSelected >= min ? null : { minSelected: true };
    };
  }

  constructor(private fb: FormBuilder) {
    this.mentoredForm = this.fb.group({
      academicSummary: ['', Validators.maxLength(500)],
      course: ['', Validators.required],
      interestArea: this.fb.array([], [Validators.required, EditMentoredModalComponent.minSelectedCheckboxes(1)])
    });
  }

  get interestAreaArray(): FormArray {
    return this.mentoredForm.get('interestArea') as FormArray;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['mentored'] && this.mentored) {
      this.mentoredForm.patchValue({
        academicSummary: this.mentored.academicSummary || '',
        course: this.mentored.course || ''
      });

      while (this.interestAreaArray.length !== 0) {
        this.interestAreaArray.removeAt(0);
      }

      if (this.mentored.interestArea) {
        const interests = Array.isArray(this.mentored.interestArea) 
          ? this.mentored.interestArea 
          : [this.mentored.interestArea];

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

      this.mentoredForm.markAsPristine();
      this.mentoredForm.markAsUntouched();
    }
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
      'TECNOLOGIA_DA_INFORMACAO': 'mentored-tech-info',
      'DESENVOLVIMENTO_DE_SOFTWARE': 'mentored-dev-software',
      'CIENCIA_DE_DADOS_E_IA': 'mentored-data-ia',
      'CIBERSEGURANCA': 'mentored-cybersec',
      'UX_UI_DESIGN': 'mentored-ux-ui',
      'ENGENHARIA_GERAL': 'mentored-eng-geral',
      'ENGENHARIA_CIVIL': 'mentored-eng-civil',
      'ENGENHARIA_DE_PRODUCAO': 'mentored-eng-prod',
      'MATEMATICA_E_ESTATISTICA': 'mentored-math-stat',
      'FISICA': 'mentored-fisica',
      'ADMINISTRACAO_E_GESTAO': 'mentored-admin-gestao',
      'EMPREENDEDORISMO_E_INOVACAO': 'mentored-empreend',
      'FINANCAS_E_CONTABILIDADE': 'mentored-financas',
      'RECURSOS_HUMANOS': 'mentored-rh',
      'LOGISTICA_E_CADEIA_DE_SUPRIMENTOS': 'mentored-logistica',
      'MARKETING_E_COMUNICACAO': 'mentored-marketing',
      'MARKETING_DIGITAL': 'mentored-marketing-digital',
      'JORNALISMO': 'mentored-jornalismo',
      'PUBLICIDADE_E_PROPAGANDA': 'mentored-publicidade',
      'COMUNICACAO_INSTITUCIONAL': 'mentored-com-institucional',
      'CIENCIAS_BIOLOGICAS_E_SAUDE': 'mentored-bio-saude',
      'MEDICINA': 'mentored-medicina',
      'PSICOLOGIA': 'mentored-psicologia',
      'NUTRICAO': 'mentored-nutricao',
      'BIOTECNOLOGIA': 'mentored-biotecnologia',
      'EDUCACAO': 'mentored-educacao',
      'CIENCIAS_HUMANAS_E_SOCIAIS': 'mentored-humanas',
      'LETRAS': 'mentored-letras',
      'HISTORIA': 'mentored-historia',
      'GEOGRAFIA': 'mentored-geografia',
      'SOCIOLOGIA': 'mentored-sociologia',
      'JURIDICO': 'mentored-juridico',
      'DIREITO_DIGITAL': 'mentored-direito-digital',
      'MEIO_AMBIENTE_E_SUSTENTABILIDADE': 'mentored-sustentabilidade'
    };
    return idMap[value] || '';
  }

  canSave(): boolean {
    return this.mentoredForm.valid && (this.mentoredForm.dirty || this.mentoredForm.touched);
  }

  onSave() {
    console.log('=== DEBUG onSave ===');
    console.log('Form valid:', this.mentoredForm.valid);
    console.log('Form dirty:', this.mentoredForm.dirty);
    console.log('Form touched:', this.mentoredForm.touched);
    console.log('canSave():', this.canSave());
    console.log('Form value:', this.mentoredForm.value);
    console.log('Form errors:', this.mentoredForm.errors);
    console.log('Interest areas array length:', this.interestAreaArray.length);
    console.log('Interest areas values:', this.interestAreaArray.value);
    
    if (this.mentoredForm.valid) {
      const updatedMentored: Mentored = {
        ...this.mentored,
        ...this.mentoredForm.value,
        interestArea: this.interestAreaArray.value 
      };
      console.log('Emitting updated mentored:', updatedMentored);
      this.save.emit(updatedMentored);
    } else {
      console.log('Form is invalid - not saving');
    }
  }

  onClose() {
    this.close.emit();
  }

  toggleSection(section: string): void {
    this.expandedSections[section] = !this.expandedSections[section];
  }
}