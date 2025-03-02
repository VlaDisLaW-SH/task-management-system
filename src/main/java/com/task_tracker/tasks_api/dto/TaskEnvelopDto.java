package com.task_tracker.tasks_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class TaskEnvelopDto {
    /**
     * Список задач
     */
    private List<TaskDto> tasks;

    /**
     * Кол-во элементов
     */
    private long totalElements;

    /**
     * Кол-во страниц
     */
    private int totalPages;
}
