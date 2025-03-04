package com.task_tracker.tasks_api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.task_tracker.tasks_api.enumeration.TaskPriority;
import com.task_tracker.tasks_api.enumeration.TaskStatus;
import com.task_tracker.technical.exception.FieldsValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TaskFilterDto {

    /**
     * Статус
     */
    private TaskStatus status;

    /**
     * Приоритет
     */
    private TaskPriority priority;

    /**
     * ID создателя задачи
     */
    private Long createdById;

    /**
     * ID исполнителя задачи
     */
    private Long assignerId;

    /**
     * Страница
     */
    private Integer page;

    /**
     * Кол-во элементов
     */
    private Integer size;

    /**
     * Значение сортировки
     */
    private String sortBy;

    @JsonCreator
    public TaskFilterDto(
            @JsonProperty("status") String status,
            @JsonProperty("priority") String priority,
            @JsonProperty("createdById") Long createdById,
            @JsonProperty("assignerId") Long assignerId
    ) {
        if (status != null) {
            try {
                this.status = TaskStatus.valueOf(status.toUpperCase());
            } catch (Exception e) {
                throw new FieldsValidationException("Некорректное значение статуса: " + status);
            }
        } else {
            this.status = null;
        }

        if (priority != null) {
            try {
                this.priority = TaskPriority.valueOf(priority.toUpperCase());
            } catch (Exception e) {
                throw new FieldsValidationException("Некорректное значение приоритета: " + priority);
            }
        } else {
            this.priority = null;
        }

        if (page == null) {
            this.page = 1;
        }
        if (size == null) {
            this.size = 10;
        }
        if (sortBy == null || sortBy.isEmpty()) {
            this.sortBy = "id";
        }

        this.createdById = createdById;
        this.assignerId = assignerId;
    }
}
