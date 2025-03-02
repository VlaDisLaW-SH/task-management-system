package com.task_tracker.comments_api.repository;

import com.task_tracker.comments_api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс-репозиторий для комментариев
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
