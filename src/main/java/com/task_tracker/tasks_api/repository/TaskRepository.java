package com.task_tracker.tasks_api.repository;

import com.task_tracker.tasks_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс-репозиторий для задач
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
