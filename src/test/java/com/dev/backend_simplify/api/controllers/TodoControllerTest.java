package com.dev.backend_simplify.api.controllers;

import com.dev.backend_simplify.common.dtos.ErrorResponse;
import com.dev.backend_simplify.common.dtos.TodoRequest;
import com.dev.backend_simplify.common.dtos.TodoResponse;
import com.dev.backend_simplify.domain.enums.Priority;
import com.dev.backend_simplify.domain.repositories.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class TodoControllerTest {

    private static final MySQLContainer<?> MYSQL_SERVER = new MySQLContainer<>("mysql:8.4");

    @Autowired
    private TodoRepository repository;

    @Autowired
    private TestRestTemplate webClient;

    @BeforeAll
    static void setUp() {
        MYSQL_SERVER.start();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldCreateATodo() {
        TodoRequest request = new TodoRequest(
                "Learn Microservices",
                "I should learn microservices",
                false,
                "HIGH"
        );

        ResponseEntity<TodoResponse> response = webClient.exchange(
                "/api/v1/todos",
                HttpMethod.POST,
                new HttpEntity<>(request),
                TodoResponse.class
        );

        assertAll(() -> {
            TodoResponse body = response.getBody();

            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertNotNull(body);
            assertEquals("Learn Microservices", body.name());
            assertEquals("I should learn microservices", body.description());
            assertEquals(false, body.completed());
            assertEquals(Priority.HIGH, body.priority());
        });

    }

    @Test
    void shouldNotCreateATodo_WhenRequestBodyIsInvalid() {
        TodoRequest request = new TodoRequest(
                "",
                null,
                null,
                ""
        );

        ResponseEntity<ErrorResponse> response = webClient.exchange(
                "/api/v1/todos",
                HttpMethod.POST,
                new HttpEntity<>(request),
                ErrorResponse.class
        );

        assertAll(() -> {
            ErrorResponse body = response.getBody();

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertNotNull(body);
            assertEquals("Missing required fields or they're invalid!", body.message());
        });

    }

}