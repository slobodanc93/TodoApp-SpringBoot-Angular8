package com.scvetkovic.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {

	@SuppressWarnings("unused")
	@Autowired 
	private TestData td;
	
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
		
	}

}
