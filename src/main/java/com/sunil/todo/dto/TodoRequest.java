package com.sunil.todo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoRequest {
    private String title;
    private String body;
    private LocalDateTime estimatedCompletion;
}
