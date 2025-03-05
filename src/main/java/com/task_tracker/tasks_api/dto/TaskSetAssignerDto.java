package com.task_tracker.tasks_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TaskSetAssignerDto {

    /**
     * ID задачи
     */
    @NotNull
    private Long taskId;

    /**
     * ID исполнителя задачи
     */
    @NotNull
    private Long assignerId;
}
