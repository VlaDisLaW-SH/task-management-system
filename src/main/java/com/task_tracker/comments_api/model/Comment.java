package com.task_tracker.comments_api.model;

import com.task_tracker.tasks_api.model.Task;
import com.task_tracker.users_api.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Комментарий
 */
@Getter
@Setter
@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    /**
     * Комментарий
     */
    @NotBlank
    @Column(name = "body")
    private String body;

    /**
     * Задача
     */
    @NotNull
    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private Task task;

    /**
     * Создатель комментария
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Дата и время создания комментария
     */
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Дата и время обновления комментария
     */
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
