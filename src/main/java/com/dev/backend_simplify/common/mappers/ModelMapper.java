package com.dev.backend_simplify.common.mappers;

import com.dev.backend_simplify.common.dtos.TodoRequest;
import com.dev.backend_simplify.common.dtos.TodoResponse;
import com.dev.backend_simplify.domain.enums.Priority;
import com.dev.backend_simplify.domain.models.Todo;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    public Todo toModel(TodoRequest request) {
        return new Todo(
                request.name(),
                request.description(),
                request.completed(),
                Priority.valueOf(request.priority())
        );
    }

    public TodoResponse toResponse(Todo model) {
        return new TodoResponse(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getCompleted(),
                model.getPriority()
        );
    }

}
