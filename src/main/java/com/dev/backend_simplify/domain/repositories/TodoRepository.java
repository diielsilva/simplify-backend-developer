package com.dev.backend_simplify.domain.repositories;

import com.dev.backend_simplify.domain.enums.Priority;
import com.dev.backend_simplify.domain.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByPriorityOrderByNameAsc(Priority priority);

}
