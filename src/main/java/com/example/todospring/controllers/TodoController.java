package com.example.todospring.controllers;

import com.example.todospring.entity.Todo;
import com.example.todospring.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @CrossOrigin
    @PostMapping("/api/todo")
    public Todo createToDo(@RequestBody() Todo toDoRequest){
        Todo todo = new Todo();
        todo.setDescription(toDoRequest.getDescription());
        todo.setDueDate(toDoRequest.getDueDate());
        todo.setStatus(toDoRequest.getStatus());
        todo.setOwner(toDoRequest.getOwner());
        todo.setPriority(toDoRequest.getPriority());
        todoService.addToDo(todo);
        return todo;
    }

    @CrossOrigin
    @GetMapping("/api/todo")
    public List<Todo> getAllToDos() {
        List<Todo> allToDos = todoService.findAllToDos();
        return allToDos;
    }

    @CrossOrigin
    @GetMapping("/api/todo/{id}")
    public ResponseEntity<Todo> getToDo(@PathVariable Long id){
        Optional<Todo> todo = todoService.findSingleToDo(id);

        if (todo.isPresent()) {
            return ResponseEntity.ok(todo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @PutMapping("/api/todo/{id}")
    public ResponseEntity<Todo> updateToDo(@PathVariable Long id, @RequestBody Todo toDoRequest){
        Todo updatedToDo = todoService.updateToDo(id, toDoRequest);
        if (updatedToDo != null){
            return ResponseEntity.ok(updatedToDo);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/api/todo/{id}")
    public void deleteToDo(@PathVariable Long id){
        todoService.deleteToDo(id);
    }
}



