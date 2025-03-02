package com.task_tracker.comments_api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentForUserDto {
    private Long id;

    /**
     * Комментарий
     */
    private String body;

    /**
     * ID задачи
     */
    private Long taskId;
}
