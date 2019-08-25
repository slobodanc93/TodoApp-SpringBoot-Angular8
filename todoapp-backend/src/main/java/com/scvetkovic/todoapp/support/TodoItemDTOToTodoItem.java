package com.scvetkovic.todoapp.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.scvetkovic.todoapp.model.TodoItem;
import com.scvetkovic.todoapp.service.TodoService;
import com.scvetkovic.todoapp.web.dto.TodoItemDTO;

@Component
public class TodoItemDTOToTodoItem implements Converter<TodoItemDTO, TodoItem> {
	
	@Autowired
	private TodoService todoService;
	
	@Override
	public TodoItem convert(TodoItemDTO source) {
		TodoItem target;
		
		if(source.getId() == null) {
			target = new TodoItem();
		} else {
			target = todoService.findById(source.getId());
		}
		target.setTitle(source.getTitle());
		target.setDate(source.getDate());
		target.setCompleted(source.getCompleted());
		
		return target;
	}
	
	public List<TodoItem> convert(List<TodoItemDTO> source) {	
		List<TodoItem> target = new ArrayList<>();	
		for(TodoItemDTO p : source) {
			target.add(convert(p));
		}	
		return target;
	}

}
