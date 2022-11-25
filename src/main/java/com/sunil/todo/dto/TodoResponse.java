package com.sunil.todo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoResponse {
    private Long id;
    private String title;
    private String body;
    private LocalDateTime estimatedCompletion;
    private LocalDateTime createdDate;
    private Boolean completed;
}
