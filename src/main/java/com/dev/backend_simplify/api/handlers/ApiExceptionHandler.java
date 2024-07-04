package com.dev.backend_simplify.api.handlers;

import com.dev.backend_simplify.common.dtos.ErrorResponse;
import com.dev.backend_simplify.domain.exceptions.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = TodoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTodoNotFound(TodoNotFoundException exception) {
        return send(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        return send(
                HttpStatus.BAD_REQUEST,
                "Missing required fields or they're invalid!"
        );
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException exception) {
        return send(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Something went wrong, if it persists contact the support!"
        );
    }

    private ResponseEntity<ErrorResponse> send(HttpStatus status, String message) {
        ErrorResponse response = new ErrorResponse(message);

        return ResponseEntity
                .status(status)
                .body(response);
    }

}
