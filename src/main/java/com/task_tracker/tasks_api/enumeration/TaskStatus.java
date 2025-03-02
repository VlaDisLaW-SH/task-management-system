package com.task_tracker.tasks_api.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Тип статуса задачи
 */
@Getter
@RequiredArgsConstructor
@Schema(enumAsRef = true)
public enum TaskStatus {
    NEW("Новая задача"),
    IN_PROGRESS("Задача взята в работу"),
    COMPLETED("Задача выполнена"),
    TO_FIXING("Задача ожидает исправлений"),
    CANCELLED("Выполнение задачи отменено"),
    CLOSED("Задача закрыта");

    private final String description;
}
