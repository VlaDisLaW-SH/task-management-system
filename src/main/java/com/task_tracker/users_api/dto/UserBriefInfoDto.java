package com.task_tracker.users_api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserBriefInfoDto {
    /**
     * ФИО
     */
    private String fullName;
    /**
     * Адрес электронной почты
     */
    private String email;
}
