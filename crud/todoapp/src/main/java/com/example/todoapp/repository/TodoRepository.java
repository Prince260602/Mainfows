package com.example.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoapp.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
