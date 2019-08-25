package com.scvetkovic.todoapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.scvetkovic.todoapp.model.TodoItem;

@Service
public interface TodoService {
	
	TodoItem save(TodoItem todoItem);
	List<TodoItem> findAll();
	TodoItem findById(Long id);
	List<TodoItem> findByDate(LocalDate date);
	List<TodoItem> findByDateAndCompleted(LocalDate date, Boolean completed);
	List<TodoItem> findByCompleted(Boolean completed);
	TodoItem delete(Long id);

}
