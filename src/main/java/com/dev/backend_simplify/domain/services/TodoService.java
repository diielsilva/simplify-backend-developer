package com.dev.backend_simplify.domain.services;

import com.dev.backend_simplify.common.dtos.TodoRequest;
import com.dev.backend_simplify.common.dtos.TodoResponse;
import com.dev.backend_simplify.common.mappers.ModelMapper;
import com.dev.backend_simplify.domain.enums.Priority;
import com.dev.backend_simplify.domain.exceptions.TodoNotFoundException;
import com.dev.backend_simplify.domain.models.Todo;
import com.dev.backend_simplify.domain.repositories.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repository;
    private final ModelMapper mapper;

    public TodoService(TodoRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public TodoResponse create(TodoRequest request) {
        Todo todo = mapper.toModel(request);

        repository.save(todo);

        return mapper.toResponse(todo);
    }

    public List<TodoResponse> findByPriority(String priority) {
        List<Todo> todos = repository.findByPriorityOrderByNameAsc(Priority.valueOf(priority));

        return todos
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public TodoResponse update(Long id, TodoRequest request) {
        Todo todo = mapper.toModel(request);
        Todo stored = findById(id);

        todo.setId(stored.getId());

        repository.save(todo);

        return mapper.toResponse(todo);
    }

    @Transactional
    public void delete(Long id) {
        Todo todo = findById(id);

        repository.delete(todo);
    }

    private Todo findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new TodoNotFoundException(String.format("Todo: %s not found!", id)));
    }
}
