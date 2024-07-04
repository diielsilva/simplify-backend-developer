package com.dev.backend_simplify.common.dtos;

import com.dev.backend_simplify.domain.enums.Priority;

public record TodoResponse(
        Long id,
        String name,
        String description,
        Boolean completed,
        Priority priority
) {
}
