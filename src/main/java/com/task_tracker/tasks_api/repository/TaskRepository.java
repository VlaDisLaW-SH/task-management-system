package com.task_tracker.tasks_api.repository;

import com.task_tracker.tasks_api.enumeration.TaskPriority;
import com.task_tracker.tasks_api.enumeration.TaskStatus;
import com.task_tracker.tasks_api.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс-репозиторий для задач
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT t FROM Task t " +
            "WHERE (:status IS NULL OR t.status = :status) " +
            "AND (:priority IS NULL OR t.priority = :priority) " +
            "AND (:createdById IS NULL OR t.createdBy.id = :createdById) " +
            "AND (:assignerId IS NULL OR t.assigner.id = :assignerId)")
    Page<Task> findByFilters(@Param("status") TaskStatus status,
                             @Param("priority") TaskPriority priority,
                             @Param("createdById") Long createdById,
                             @Param("assignerId") Long assignerId,
                             Pageable pageable);
}
