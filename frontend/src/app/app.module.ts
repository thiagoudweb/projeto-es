import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { AppComponent } from './app.component';
import { MentorProfileComponent } from './mentor/mentor-profile.component';
import { EditMentorModalComponent } from './mentor/edit-mentor-modal.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    CommonModule,
    AppComponent,
    MentorProfileComponent,
    EditMentorModalComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
