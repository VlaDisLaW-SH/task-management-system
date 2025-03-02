package com.task_tracker.tasks_api.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Тип приоритета задачи
 */
@Getter
@RequiredArgsConstructor
@Schema(enumAsRef = true)
public enum TaskPriority {
    LOW("Низкий"),
    MIDDLE("Средний"),
    HIGH("Высокий");

    private final String description;
}
