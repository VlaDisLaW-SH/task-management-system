package com.task_tracker.users_api.repository;

import com.task_tracker.users_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс-репозиторий для пользователей
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Проверка на наличие адреса электронной почты в репозитории
     * @param email адрес электронной почты
     * @return булево значение
     */
    boolean existsByEmail(String email);
}
