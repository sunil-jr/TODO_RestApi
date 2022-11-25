package com.sunil.todo.controller;

import com.sunil.todo.dto.*;
import com.sunil.todo.exception.TodoException;
import com.sunil.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseDto addTodo(@RequestBody TodoRequest addTodoRequest) {
        try {
            TodoResponse data = todoService.create(addTodoRequest);
            return createResponse("Added successfully", data);
        } catch (TodoException e) {
            return createResponse(e.getMessage());
        } catch (Exception e) {
            return createResponse("Internal Server Error");
        }
    }

    @GetMapping("/{id}")
    public ResponseDto getTodo(@PathVariable("id") Long uuid) {
        try {
            return createResponse("Fetched successfully", todoService.getById(uuid));
        } catch (TodoException e) {
            return createResponse(e.getMessage());
        } catch (Exception e) {
            return createResponse("Internal Server Error");
        }
    }

    @GetMapping
    public ResponseDto getAllTodoResponse(@RequestParam(value = "completed", required = false, defaultValue = "false") Boolean completed) {
        try {
            return createResponse("Fetched all successfully", todoService.getAll(completed));
        } catch (Exception e) {
            return createResponse("Internal Server Error");
        }
    }

    @PutMapping("/{id}")
    public ResponseDto updateTodoRequest(@PathVariable("id") Long id, @RequestBody TodoRequest
            updateTodoRequest) {
        try {
            return createResponse("Updated successfully", todoService.update(id, updateTodoRequest));
        } catch (TodoException e) {
            return createResponse(e.getMessage());
        } catch (Exception e) {
            return createResponse("Internal Server Error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseDto delete(@PathVariable("id") Long id) {
        try {
            todoService.delete(id);
            return createResponse("Deleted successfully");
        } catch (TodoException e) {
            return createResponse(e.getMessage());
        } catch (Exception e) {
            return createResponse("Internal Server Error");
        }
    }

    @PostMapping("/complete/{id}")
    public ResponseDto completeTodo(@PathVariable("id") Long id) {
        return createResponse("Todo completed", todoService.complete(id));
    }


    private ResponseDto createResponse(String message) {
        return createResponse(message, null);
    }

    private ResponseDto createResponse(String message, Object data) {
        return ResponseDto.builder()
                .message(message)
                .data(data)
                .build();
    }


}
