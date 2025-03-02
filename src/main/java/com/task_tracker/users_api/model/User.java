package com.task_tracker.users_api.model;

import com.task_tracker.comments_api.model.Comment;
import com.task_tracker.tasks_api.model.Task;
import com.task_tracker.users_api.enumeration.UserAccessType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Позьзователь
 */
@Getter
@Setter
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    /**
     * ФИО
     */
    @NotBlank
    @Size(max = 250)
    @Column(name = "full_name")
    private String fullName;

    /**
     * Email
     */
    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    /**
     * Пароль
     */
    @Column(name = "password",nullable = false, length = 255)
    private String password;

    /**
     * Роль пользователя в системе
     */
    @NotNull
    @Column(name = "access_type")
    @Enumerated(EnumType.STRING)
    private UserAccessType accessType;

    /**
     * Список задач созданных пользователем
     */
    @OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Task> listTasksCreatedBy = new ArrayList<>();

    /**
     * Список задач, в которых пользователь является исполнителем
     */
    @OneToMany(mappedBy = "assigner", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Task> listTasksAssigner = new ArrayList<>();

    /**
     * Список комментариев пользователя
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Comment> comments = new ArrayList<>();

    /**
     * Дата и время создания пользователя
     */
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
