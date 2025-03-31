package com.task_tracker.tasks_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TaskCreateDto {
    /**
     * Заголовок
     */
    @NotBlank
    @Size(max = 100)
    private String title;

    /**
     * Описание
     */
    @NotBlank
    private String description;

    /**
     * Статус
     */
    @NotNull(message = "Статус задачи не может быть пустым")// todo notblank
    private String status;

    /**
     * Приоритет
     */
    @NotNull(message = "Приоритет задачи не может быть пустым")
    private String priority;

    /**
     * ID создателя задачи
     */
    @NotNull
    private Long createdById;

    /**
     * ID исполнителя задачи
     */
    private Long assignerId;
}
