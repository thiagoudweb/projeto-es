import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Mentor } from '../../entity/mentor';

@Component({
  selector: 'app-delete-mentor-modal',
  templateUrl: './delete-mentor-modal.component.html',
  styleUrls: ['./delete-mentor-modal.component.css'],
  standalone: true 
})
export class DeleteMentorModalComponent {
  @Input() mentor!: Mentor;
  @Output() confirmDelete = new EventEmitter<void>();
  @Output() cancelDelete = new EventEmitter<void>();

  ngOnInit() {
    console.log('Modal inicializado com mentor:', this.mentor);
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
