import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Mentored } from '../../entity/mentored';

@Component({
  selector: 'app-delete-mentored-modal',
  templateUrl: './delete-mentored-modal.component.html',
  styleUrls: ['./delete-mentored-modal.component.css'],
  standalone: true 
})
export class DeleteMentoredModalComponent {
  @Input() mentored!: Mentored;
  @Output() confirmDelete = new EventEmitter<void>();
  @Output() cancelDelete = new EventEmitter<void>();

  ngOnInit() {
    console.log('Modal inicializado com mentorado:', this.mentored);
  }

  onConfirm() {
    console.log('Botão Excluir clicado no modal');
    console.log('Emitindo evento confirmDelete');
    this.confirmDelete.emit();
  }

  onCancel() {
    console.log('Botão Cancelar clicado no modal');
    this.cancelDelete.emit();
  }
}
