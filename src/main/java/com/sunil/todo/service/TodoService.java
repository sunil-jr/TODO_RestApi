package com.sunil.todo.service;

import com.sunil.todo.dto.*;

import java.util.List;

public interface TodoService {
    TodoResponse create(TodoRequest addTodoRequest);

    TodoResponse getById(Long uuid);

    List<TodoResponse> getAll(Boolean completed);

    TodoResponse update(Long id, TodoRequest updateTodoRequest);

    void delete(Long id);

    TodoResponse complete(Long id);
}
