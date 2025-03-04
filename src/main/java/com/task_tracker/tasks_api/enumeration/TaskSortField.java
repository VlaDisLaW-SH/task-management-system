package com.task_tracker.tasks_api.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Допустимые значения для сортировки задачи
 */
@Getter
@RequiredArgsConstructor
@Schema(enumAsRef = true)
public enum TaskSortField {
    ID("id"),
    TITLE("title"),
    DESCRIPTION("description"),
    STATUS("status"),
    PRIORITY("priority"),
    CREATED_BY("createdBy"),
    ASSIGNER("assigner"),
    CREATED_AT("createdAt"),
    UPDATED_AT("updatedAt");

    private final String field;
}
