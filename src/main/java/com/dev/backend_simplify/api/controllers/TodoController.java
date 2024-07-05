package com.dev.backend_simplify.api.controllers;

import com.dev.backend_simplify.common.dtos.TodoRequest;
import com.dev.backend_simplify.common.dtos.TodoResponse;
import com.dev.backend_simplify.domain.services.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody @Valid TodoRequest request) {
        TodoResponse response = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> findByPriority(@RequestParam String priority) {
        List<TodoResponse> response = service.findByPriority(priority);

        return ResponseEntity
                .ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }

}
