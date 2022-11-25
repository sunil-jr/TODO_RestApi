package com.sunil.todo.mapper;

import com.sunil.todo.dto.*;
import com.sunil.todo.entity.TodoEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TodoMapper {

    public TodoEntity toEntity(TodoRequest addTodoRequest) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(addTodoRequest.getTitle());
        todoEntity.setBody(addTodoRequest.getBody());
        todoEntity.setEstimatedCompletion(addTodoRequest.getEstimatedCompletion());
        todoEntity.setCreatedDate(LocalDateTime.now());
        return todoEntity;
    }

    public TodoEntity toEntity(TodoEntity entity, TodoRequest updateTodoRequest) {
        entity.setTitle(updateTodoRequest.getTitle());
        entity.setBody(updateTodoRequest.getBody());
        entity.setEstimatedCompletion(updateTodoRequest.getEstimatedCompletion());
        return entity;
    }

    public TodoResponse toResponse(TodoEntity todoEntity) {
        TodoResponse todoResponse = new TodoResponse();
        todoResponse.setId(todoEntity.getId());
        todoResponse.setTitle(todoEntity.getTitle());
        todoResponse.setBody(todoEntity.getBody());
        todoResponse.setEstimatedCompletion(todoEntity.getEstimatedCompletion());
        todoResponse.setCreatedDate(todoEntity.getCreatedDate());
        todoResponse.setCompleted(todoEntity.getCompleted());
        return todoResponse;
    }

    public List<TodoResponse> toResponse(List<TodoEntity> entityList) {
        List<TodoResponse> todoAllList = new ArrayList<>();
        for (TodoEntity entity : entityList)
            todoAllList.add(toResponse(entity));
        return todoAllList;
    }

}
