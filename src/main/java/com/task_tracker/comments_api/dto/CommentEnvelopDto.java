package com.task_tracker.comments_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class CommentEnvelopDto {
    /**
     * Список комментариев
     */
    private List<CommentDto> comments;

    /**
     * Кол-во элементов
     */
    private long totalElements;

    /**
     * Кол-во страниц
     */
    private int totalPages;
}
