package com.scvetkovic.todoapp;

import java.time.LocalDate;
import java.time.Month;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scvetkovic.todoapp.model.TodoItem;
import com.scvetkovic.todoapp.service.TodoService;

@Component
public class TestData {
	
	@Autowired
	private TodoService todoService;
	
	@PostConstruct
	public void init() {
		TodoItem t1 =  new TodoItem();
		t1.setTitle("Refactor REST API for payment");
		t1.setDate(LocalDate.of(2019, Month.JANUARY, 18));
		todoService.save(t1);
		
		TodoItem t2 =  new TodoItem();
		t2.setTitle("Arrange skype call with project manager");
		t2.setDate(LocalDate.of(2019, Month.FEBRUARY, 1));
		todoService.save(t2);
		
		TodoItem t3 =  new TodoItem();
		t3.setTitle("Organize team meeting");
		t3.setDate(LocalDate.of(2019, Month.JULY, 4));
		todoService.save(t3);
		
		TodoItem t4 =  new TodoItem();
		t4.setTitle("Prepare mockup for new mobile application");
		t4.setDate(LocalDate.of(2019, Month.JANUARY, 1));
		todoService.save(t4);
		
	}

}
