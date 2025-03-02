package com.task_tracker.comments_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommentCreateDto {
    /**
     * Комментарий
     */
    @NotBlank
    private String body;

    /**
     * ID задачи
     */
    @NotNull
    private Long taskId;

    /**
     * ID пользователя
     */
    @NotNull
    private Long userId;
}
