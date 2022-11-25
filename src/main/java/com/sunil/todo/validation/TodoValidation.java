package com.sunil.todo.validation;

import com.sunil.todo.dto.TodoRequest;
import com.sunil.todo.exception.TodoException;
import org.springframework.stereotype.Component;

@Component
public class TodoValidation {

    public void validate(TodoRequest request) {
        if (request.getTitle() == null || request.getTitle().trim().equals(""))
            throw new TodoException("Title is required!");
        if (request.getBody() == null || request.getBody().trim().equals(""))
            throw new TodoException("Body is required!");
        if (request.getEstimatedCompletion() == null)
            throw new TodoException("Estimated Completion is required!");
    }
}
