package com.scvetkovic.todoapp.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scvetkovic.todoapp.model.TodoItem;
import com.scvetkovic.todoapp.repository.TodoRepository;
import com.scvetkovic.todoapp.service.TodoService;

@Service
public class JpaTodoService implements TodoService {
	
	@Autowired
	private  TodoRepository todoRepository;
	
	@Override
	public TodoItem save(TodoItem todoItem) {
		return todoRepository.save(todoItem);
	}
	
	@Override
	public List<TodoItem> findAll() {
		return todoRepository.findAll();
	}
	
	@Override
	public TodoItem findById(Long id) {
		TodoItem todoItem;
		try {
			todoItem = todoRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			todoItem = null;
		}
		return todoItem;
	}
	
	@Override
	public List<TodoItem> findByDate(LocalDate date) {
		return todoRepository.findByDate(date);
	}
	
	@Override
	public TodoItem delete(Long id) {
		TodoItem todoItem = findById(id);
		if(todoItem != null) {
			todoRepository.delete(todoItem);
		}
		return todoItem;

	}

	@Override
	public List<TodoItem> findByDateAndCompleted(LocalDate date, Boolean completed) {
		return todoRepository.findByDateAndCompleted(date, completed);
	}

	@Override
	public List<TodoItem> findByCompleted(Boolean completed) {
		return todoRepository.findByCompleted(completed);
	}

}
