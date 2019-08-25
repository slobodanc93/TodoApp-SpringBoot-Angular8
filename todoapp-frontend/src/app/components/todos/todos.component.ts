import { Component, OnInit } from '@angular/core';
import { Todo } from '../../models/Todo';
import { TodoService } from '../../services/todo.service';

@Component({
  selector: 'app-todos',
  templateUrl: './todos.component.html',
  styleUrls: ['./todos.component.css']
})
export class TodosComponent implements OnInit {

  todos: Todo[];
  date;

  constructor(private todoService:TodoService) { 
  }

  ngOnInit() {
  }

  deleteTodo(todo:Todo) {
    this.todoService.deleteTodo(todo).subscribe();
    this.todos = this.todos.filter(t => t.id != todo.id);  
  }

  addTodo(todo:Todo) {
    todo.date = this.date;
    this.todoService.addTodo(todo).subscribe(todo => {
      this.todos.push(todo);
    });
  }

  getTodos(date:Date) {
    this.todoService.getTodos(date).subscribe(todos => {
      this.todos = todos;
    });
    this.date = date;
  }

}
