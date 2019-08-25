import {Component, OnInit, Input, EventEmitter, Output} from '@angular/core';
import {MomentDateAdapter} from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material';
import * as _moment from 'moment';

const moment = _moment;

// See the Moment.js docs for the meaning of these formats:
// https://momentjs.com/docs/#/displaying/format/
export const MY_FORMATS = {
  parse: {
    dateInput: 'DD.MM.YYYY',
  },
  display: {
    dateInput: 'DD.MM.YYYY',
    monthYearLabel: 'MM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'YYYY',
  },
};

/** @title Datepicker with custom formats */
@Component({
  selector: 'app-todo-date',
  templateUrl: './todo-date.component.html',
  styleUrls: ['./todo-date.component.css'],
  providers: [
    // `MomentDateAdapter` can be automatically provided by importing `MomentDateModule` in your
    // application's root module. We provide it at the component level here, due to limitations of
    // our example generation script.
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},

    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
})
export class TodoDateComponent implements OnInit {
 
  @Input() date: Date;
  @Output() getTodos: EventEmitter<any> = new EventEmitter();

  constructor() {
    this.date = new Date();
   }

  ngOnInit() {
    const selectedDate = this.constructDate(this.date);
    this.getTodos.emit(selectedDate);
  }

  onChangeDate(date) {
    const selectedDate = this.constructDate(date._d);
    this.getTodos.emit(selectedDate);
  }

  constructDate(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    return day + "-" + month + "-" + year;
  }
}
