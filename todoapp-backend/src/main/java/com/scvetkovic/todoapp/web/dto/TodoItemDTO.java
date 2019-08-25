package com.scvetkovic.todoapp.web.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TodoItemDTO {
	
	private Long id;
	@NotBlank(message = "Title cannot be blank")
	private String title;
	@JsonFormat(pattern="d-M-yyyy")
	private LocalDate date;
	private Boolean completed = false;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	
	

}
