package com.task_tracker.comments_api.dto;

import com.task_tracker.users_api.dto.UserBriefInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentForTaskDto {
    private Long id;

    /**
     * Комментарий
     */
    private String body;

    /**
     * Краткая информация о создателе комментария
     */
    private UserBriefInfoDto userInfo;
}
