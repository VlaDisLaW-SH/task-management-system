package com.task_tracker.tasks_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.task_tracker.comments_api.dto.CommentForTaskDto;
import com.task_tracker.tasks_api.enumeration.TaskPriority;
import com.task_tracker.tasks_api.enumeration.TaskStatus;
import com.task_tracker.users_api.dto.UserBriefInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TaskDto {

    private Long id;

    /**
     * Заголовок
     */
    private String title;

    /**
     * Описание
     */
    private String description;

    /**
     * Статус
     */
    private TaskStatus status;

    /**
     * Приоритет
     */
    private TaskPriority priority;

    /**
     * Краткая информация о создателе задачи
     */
    private UserBriefInfoDto createdByInfo;

    /**
     * Краткая информация о исполнителе задачи
     */
    private UserBriefInfoDto assignerInfo;

    /**
     * Список комментариев к задаче, содержащий краткую информацию о комментарии
     */
    private List<CommentForTaskDto> commentsInfo = new ArrayList<>();

    /**
     * Дата и время создания задачи
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    /**
     * Дата и время обновления задачи
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}
