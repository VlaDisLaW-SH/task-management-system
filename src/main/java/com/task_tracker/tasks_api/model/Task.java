package com.task_tracker.tasks_api.model;

import com.task_tracker.comments_api.model.Comment;
import com.task_tracker.tasks_api.enumeration.TaskPriority;
import com.task_tracker.tasks_api.enumeration.TaskStatus;
import com.task_tracker.users_api.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Задача
 */
@Getter
@Setter
@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    /**
     * Заголовок
     */
    @NotBlank
    @Size(max = 100)
    @Column(name = "title")
    private String title;

    /**
     * Описание
     */
    @NotBlank
    @Column(name = "description")
    private String description;

    /**
     * Статус
     */
    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    /**
     * Приоритет
     */
    @NotNull
    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    /**
     * Список комментариев
     */
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    /**
     * Создатель задачи
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "createdBy_id")
    private User createdBy;

    /**
     * Исполнитель задачи
     */
    @ManyToOne
    @JoinColumn(name = "assigner_id")
    private User assigner;

    /**
     * Дата и время создания задачи
     */
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Дата и время обновления задачи
     */
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
