package com.example.todospring.controllers;

import com.example.todospring.entity.Todo;
import com.example.todospring.service.TodoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

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

    @GetMapping("/api/todo")
    public List<Todo> getAllToDos(){
        List<Todo> allToDos = todoService.findAllToDos();
        return allToDos;
    }

    @GetMapping("/api/todo/{id}")
    public Todo getToDo(@PathVariable Long id){
        Todo todo = todoService.findSingleToDo(id).orElseThrow(
                () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
            )
        );
        
        return todo;
    }

    @PutMapping("/api/todo/{id}")
    public Todo updateToDo(@PathVariable Long id, @RequestBody Todo toDoRequest){
        Todo updatedToDo = todoService.updateToDo(id, toDoRequest);
        return updatedToDo;
    }

    @DeleteMapping("/api/todo/{id}")
    public void deleteToDo(@PathVariable Long id){
        todoService.deleteToDo(id);
    }
}



