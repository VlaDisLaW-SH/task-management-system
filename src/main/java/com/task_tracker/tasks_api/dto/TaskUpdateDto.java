package com.task_tracker.tasks_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
@NoArgsConstructor
public class TaskUpdateDto {
    /**
     * Заголовок
     */
    @NotBlank
    @Size(max = 100)
    private JsonNullable<String> title;

    /**
     * Описание
     */
    @NotBlank
    private JsonNullable<String> description;

    /**
     * Статус
     */
    private String status;

    /**
     * Приоритет
     */
    private String priority;
}
