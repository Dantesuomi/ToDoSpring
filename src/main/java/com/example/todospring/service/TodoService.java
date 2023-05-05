package com.example.todospring.service;

import com.example.todospring.entity.Todo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {
    
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findAllToDos() {
        return todoRepository.findAll();
    }
    @Transactional
    public void addToDo(Todo todo){
        todoRepository.save(todo);
    }

    public Optional<Todo> findSingleToDo(Long id){
        return todoRepository.findById(id);
    }

    @Transactional
    public Todo updateToDo(Long id, Todo todo){
        Todo toDoToUpdate = todoRepository.findById(id).orElse(null);
        if(toDoToUpdate == null){
            return null;
        }
        toDoToUpdate.setDescription(todo.getDescription());
        toDoToUpdate.setDueDate(todo.getDueDate());
        toDoToUpdate.setStatus(todo.getStatus());
        toDoToUpdate.setOwner(todo.getOwner());
        toDoToUpdate.setPriority(todo.getPriority());
        todoRepository.save(toDoToUpdate);

        return toDoToUpdate;
    }

    @Transactional
    public void deleteToDo(Long id){
        todoRepository.deleteById(id);
    }
}
