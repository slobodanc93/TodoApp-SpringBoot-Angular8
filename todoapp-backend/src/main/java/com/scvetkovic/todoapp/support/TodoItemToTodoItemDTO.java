package com.scvetkovic.todoapp.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.scvetkovic.todoapp.model.TodoItem;
import com.scvetkovic.todoapp.web.dto.TodoItemDTO;

@Component
public class TodoItemToTodoItemDTO implements Converter<TodoItem, TodoItemDTO>  {

	@Override
	public TodoItemDTO convert(TodoItem source) {
		if(source==null){
			return null;
		}
		TodoItemDTO target = new TodoItemDTO();
		target.setId(source.getId());
		target.setTitle(source.getTitle());
		target.setDate(source.getDate());
		target.setCompleted(source.getCompleted());
		
		return target;
	}
	
	public List<TodoItemDTO> convert(List<TodoItem> source) {
		List<TodoItemDTO> target = new ArrayList<>();	
		for(TodoItem p : source) {
			target.add(convert(p));
		}
		return target;
	}

}
