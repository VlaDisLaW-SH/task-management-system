package com.task_tracker.tasks_api.repository;

import com.task_tracker.tasks_api.dto.TaskFilterDto;
import com.task_tracker.tasks_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Интерфейс-репозиторий для задач
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t " +
            "WHERE (:#{#filter.status} IS NULL OR t.status = :#{#filter.status}) " +
            "AND (:#{#filter.priority} IS NULL OR t.priority = :#{#filter.priority}) " +
            "AND (:#{#filter.createdById} IS NULL OR t.createdBy.id = :#{#filter.createdById}) " +
            "AND (:#{#filter.assignerId} IS NULL OR t.assigner.id = :#{#filter.assignerId})")
    List<Task> findByFilters(@Param("filter") TaskFilterDto filter);
}
