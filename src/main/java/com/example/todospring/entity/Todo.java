package com.example.todospring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity(name= "todo")
public class Todo {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private Date dueDate;
    private TodoStatus status;
    private String owner;
    private Priority priority;
    @CreationTimestamp
    private Date createdDate;
    @UpdateTimestamp
    private Date modifyDate;
}