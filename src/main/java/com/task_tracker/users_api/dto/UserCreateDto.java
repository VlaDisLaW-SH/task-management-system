package com.task_tracker.users_api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserCreateDto {
    /**
     * ФИО
     */
    @NotBlank
    @Size(max = 250)
    private String fullName;

    /**
     * Адрес электронной почты
     */
    @NotNull
    @Column(unique = true)
    @Email
    private String email;

    /**
     * Пароль
     */
    @NotNull
    @Size(min = 8, max = 72, message = "Пароль должен быть от 8 до 72 символов")
    private String password;

    /**
     * Роль пользователя в системе
     */
    @NotNull(message = "Необходимо назначить роль пользователя в системе")
    @Pattern(regexp = "USER|ADMINISTRATOR", message = "Некорректный тип доступа")
    private String accessType;
}
