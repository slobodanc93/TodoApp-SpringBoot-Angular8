package com.scvetkovic.todoapp.web.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scvetkovic.todoapp.model.TodoItem;
import com.scvetkovic.todoapp.service.TodoService;
import com.scvetkovic.todoapp.support.TodoItemDTOToTodoItem;
import com.scvetkovic.todoapp.support.TodoItemToTodoItemDTO;
import com.scvetkovic.todoapp.web.dto.TodoItemDTO;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private TodoItemToTodoItemDTO toDTO;
	
	@Autowired
	private TodoItemDTOToTodoItem toTodoItem;
	
	
	/*** GET ALL WITH OPTIONAL SEARCH PARAMETERS ***/
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TodoItemDTO>> get (
			@RequestParam(required=false) String date,
			@RequestParam(required=false) Boolean completed) {
		
		List<TodoItem> todoItems;
		
		if(date != null && 	completed == null) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
				LocalDate localDate = LocalDate.parse(date, formatter);
				todoItems = todoService.findByDate(localDate);
			} catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else if(date != null && completed != null) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
				LocalDate localDate = LocalDate.parse(date, formatter);
				todoItems = todoService.findByDateAndCompleted(localDate, completed);
			} catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else if(date == null && completed != null){
			todoItems = todoService.findByCompleted(completed);
		} else {
			todoItems = todoService.findAll();
		}
		
		return new ResponseEntity<>(
				toDTO.convert(todoItems), 
				HttpStatus.OK);	
	}
	
	/*** GET ONE ***/
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<TodoItemDTO> get (
			@PathVariable Long id) {
		
		TodoItem todoItem = todoService.findById(id);
		
		if (todoItem == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(
				toDTO.convert(todoItem), 
				HttpStatus.OK);
	}
	
	
	/*** POST ***/
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> addOrUpdate (
			@Valid @RequestBody TodoItemDTO newTodo,
			BindingResult result) {
		
		if(result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		
		TodoItem todoItem = toTodoItem.convert(newTodo);
		todoService.save(todoItem);
		return new ResponseEntity<TodoItemDTO>(
				toDTO.convert(todoItem), 
				HttpStatus.CREATED);	
	}
	
	/*** PUT ***/
	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	public ResponseEntity<TodoItemDTO> edit(
			@PathVariable Long id,
			@Valid @RequestBody TodoItemDTO izmenjena){
		
		if(!id.equals(izmenjena.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		TodoItem todoItem = toTodoItem.convert(izmenjena); 
		todoService.save(todoItem);
		
		return new ResponseEntity<>(
				toDTO.convert(todoItem), 
				HttpStatus.OK);
	}
	
	/*** DELETE ***/
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<TodoItemDTO> delete (
			@PathVariable Long id) {
		
		TodoItem deleted = todoService.delete(id);
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(
				toDTO.convert(deleted), 
				HttpStatus.OK);		
	}
	
	/*** EXCEPTION HANDLER ***/
	@ExceptionHandler(value= {DataIntegrityViolationException.class, IllegalArgumentException.class})
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}


}
