package com.tracker.goals.repository;

import com.tracker.goals.model.entity.task.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface TaskRepo extends JpaRepository<Task, UUID> {

    @Modifying
    @Query("UPDATE Task t SET t.isDone = :isDone " +
           "WHERE t.id = :id")
    @Transactional
    void updateStatusTask(@Param("id") UUID id, @Param("isDone") Boolean isDone);

    @Query(value = "SELECT * FROM tasks WHERE EXTRACT(EPOCH FROM now() - create_at)/3600 <= 16", nativeQuery = true)
    List<Task> getAllThisDay(Pageable pageable);
}
