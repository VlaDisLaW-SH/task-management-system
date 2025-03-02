package com.task_tracker.comments_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommentUpdateDto {
    /**
     * Комментарий
     */
    @NotBlank
    private String body;
}
