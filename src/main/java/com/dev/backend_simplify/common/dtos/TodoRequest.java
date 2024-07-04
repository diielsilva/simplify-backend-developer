package com.dev.backend_simplify.common.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TodoRequest(
        @NotBlank(message = "Name cannot be null or empty!") String name,
        @NotBlank(message = "Description cannot be null or empty!") String description,
        @NotNull(message = "Completed cannot be null") Boolean completed,
        @NotBlank(message = "Priority cannot be null or empty!") String priority
) {
}
