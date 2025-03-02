package com.task_tracker.users_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.task_tracker.comments_api.dto.CommentForUserDto;
import com.task_tracker.users_api.enumeration.UserAccessType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {
    private Long id;

    /**
     * ФИО
     */
    private String fullName;

    /**
     * Адрес электронной почты
     */
    private String email;

    /**
     * Роль пользователя в системе
     */
    private UserAccessType accessType;

    /**
     * Список задач созданных пользователем
     */
    private List<Long> listIdTasksCreatedBy = new ArrayList<>();

    /**
     * Список задач, в которых пользователь является исполнителем
     */
    private List<Long> listIdTasksPerformer = new ArrayList<>();

    /**
     * Список комментариев пользователя
     */
    private List<CommentForUserDto> comments = new ArrayList<>();

    /**
     * Дата и время создания пользователя
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
}
