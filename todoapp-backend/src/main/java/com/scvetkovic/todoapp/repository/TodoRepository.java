package com.scvetkovic.todoapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scvetkovic.todoapp.model.TodoItem;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Long> {
	
	List<TodoItem> findByDate(LocalDate date);
	List<TodoItem> findByCompleted(Boolean completed);
	
	@Query("SELECT t FROM TodoItem t WHERE "
			+ "(:date is NULL or t.date = :date) AND "
			+ "(t.completed = :completed)"
			)
	List<TodoItem> findByDateAndCompleted (
			@Param("date") LocalDate date,
			@Param("completed") Boolean completed
			);

}
