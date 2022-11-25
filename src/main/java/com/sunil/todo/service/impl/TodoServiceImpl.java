package com.sunil.todo.service.impl;

import com.sunil.todo.dto.*;
import com.sunil.todo.entity.TodoEntity;
import com.sunil.todo.exception.TodoException;
import com.sunil.todo.mapper.TodoMapper;
import com.sunil.todo.repository.TodoRepository;
import com.sunil.todo.service.TodoService;
import com.sunil.todo.validation.TodoValidation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final TodoValidation todoValidation;

    public TodoServiceImpl(TodoRepository todoRepository, TodoMapper todoMapper, TodoValidation todoValidation) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
        this.todoValidation = todoValidation;
    }

    @Override
    public TodoResponse create(TodoRequest addTodoRequest) {
        todoValidation.validate(addTodoRequest);
        TodoEntity entity = todoMapper.toEntity(addTodoRequest);
        entity = todoRepository.save(entity);
        return todoMapper.toResponse(entity);
    }

    @Override
    public TodoResponse getById(Long id) {
        Optional<TodoEntity> entity = todoRepository.findById(id);
        if (entity.isEmpty()) throw new TodoException("Todo with id:" + id + " doesn't exist");
        return todoMapper.toResponse(entity.get());
    }

    @Override
    public List<TodoResponse> getAll(Boolean completed) {
        List<TodoEntity> entityList = todoRepository.findByCompleted(completed);
        return todoMapper.toResponse(entityList);
    }

    @Override
    public TodoResponse update(Long id, TodoRequest updateTodoRequest) {
        todoValidation.validate(updateTodoRequest);
        Optional<TodoEntity> entityOptional = todoRepository.findById(id);
        if (entityOptional.isEmpty())
            throw new TodoException("Todo with id:" + id + " doesn't exist");
        TodoEntity entity = entityOptional.get();
        if (entity.getCompleted())
            throw new TodoException("Todo is already completed!");
        entity = todoMapper.toEntity(entity, updateTodoRequest);
        entity = todoRepository.save(entity);
        return todoMapper.toResponse(entity);
    }

    public void delete(Long id) {
        Optional<TodoEntity> entity = todoRepository.findById(id);
        if (entity.isEmpty())
            throw new TodoException("Todo with id:" + id + " doesn't exist");
        if (entity.get().getCompleted())
            throw new TodoException("Todo is already completed!");
        todoRepository.deleteById(id);
    }

    @Override
    public TodoResponse complete(Long id) {
        Optional<TodoEntity> entityOptional = todoRepository.findById(id);
        if (entityOptional.isEmpty())
            throw new TodoException("Todo with id:" + id + " doesn't exist");
        TodoEntity entity = entityOptional.get();
        if (entity.getCompleted())
            throw new TodoException("Todo is already completed!");
        entity.setCompleted(true);
        entity = todoRepository.save(entity);
        return todoMapper.toResponse(entity);
    }


}
