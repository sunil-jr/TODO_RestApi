package com.sunil.todo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "todo")
public class TodoEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String body;
    private LocalDateTime createdDate;
    private LocalDateTime completionDate;
    private LocalDateTime estimatedCompletion;
    private Boolean completed = false;
}
