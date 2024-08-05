package com.example.todoapp.controller;

import com.example.todoapp.model.Todo;
import com.example.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo = todoService.getTodoById(id);
        return todo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo savedTodo = todoService.saveTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        if (!todoService.getTodoById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        todo.setId(id);
        Todo updatedTodo = todoService.saveTodo(todo);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) {
        if (!todoService.getTodoById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        todoService.deleteTodoById(id);
        return ResponseEntity.noContent().build();
    }
}
