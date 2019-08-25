import { NgModule } from '@angular/core';

import {
  MatButtonModule,
  MatCheckboxModule,
  MatToolbarModule,
  MatInputModule,
  MatProgressSpinnerModule, 
  MatFormFieldModule,
  MatCardModule,
  MatMenuModule, 
  MatIconModule,
  MatDatepickerModule,
  MatNativeDateModule} from '@angular/material';


@NgModule({
  imports: [
    MatButtonModule, 
    MatCheckboxModule,
    MatToolbarModule,
    MatInputModule,
    MatProgressSpinnerModule, 
    MatFormFieldModule, 
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule],
  exports: [
    MatButtonModule, 
    MatCheckboxModule,
    MatToolbarModule,
    MatInputModule,
    MatProgressSpinnerModule, 
    MatFormFieldModule, 
    MatCardModule,
    MatMenuModule, 
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule]
})


export class MaterialModules{}