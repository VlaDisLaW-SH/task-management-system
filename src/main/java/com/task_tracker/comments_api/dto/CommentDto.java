package com.task_tracker.comments_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentDto {
    private Long id;

    /**
     * Комментарий
     */
    private String body;

    /**
     * ID задачи
     */
    private Long taskId;

    /**
     * ID пользователя
     */
    private Long userId;

    /**
     * Дата и время создания комментария
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    /**
     * Дата и время обновления комментария
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}
