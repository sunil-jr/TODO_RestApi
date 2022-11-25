package com.sunil.todo.repository;

import com.sunil.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    //    @Query("SELECT t FROM TodoEntity t WHERE t.completed=:com")
    List<TodoEntity> findByCompleted(@Param("com") Boolean completed);
}
