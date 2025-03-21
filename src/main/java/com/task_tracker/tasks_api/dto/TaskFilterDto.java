package com.task_tracker.tasks_api.dto;

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
    private String status;

    /**
     * Приоритет
     */
    private String priority;

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
}
