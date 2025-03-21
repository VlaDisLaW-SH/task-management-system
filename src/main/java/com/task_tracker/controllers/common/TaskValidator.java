package com.task_tracker.controllers.common;

import com.task_tracker.tasks_api.dto.TaskFilterDto;
import com.task_tracker.tasks_api.enumeration.TaskPriority;
import com.task_tracker.tasks_api.enumeration.TaskSortField;
import com.task_tracker.tasks_api.enumeration.TaskStatus;
import com.task_tracker.technical.exception.FieldsValidationException;
import com.task_tracker.users_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class TaskValidator {

    private final UserRepository userRepository;

    public void validateFilterTask(TaskFilterDto filterDto) {
        if (filterDto.getStatus() != null) {
            if (!EnumUtils.isValidEnumIgnoreCase(TaskStatus.class, filterDto.getStatus())) {
                throw new FieldsValidationException("Некорректное значение статуса: " + filterDto.getStatus());
            }
        }
        if (filterDto.getPriority() != null) {
            if (!EnumUtils.isValidEnumIgnoreCase(TaskPriority.class, filterDto.getPriority())) {
                throw new FieldsValidationException("Некорректное значение приоритета: " + filterDto.getPriority());
            }
        }
        if (filterDto.getCreatedById() != null) {
            if (!userRepository.existsById(filterDto.getCreatedById())) {
                throw new FieldsValidationException("Пользователь с ID " + filterDto.getCreatedById()
                        + " не зарегистрирован в системе");
            }
        }
        if (filterDto.getAssignerId() != null) {
            if (!userRepository.existsById(filterDto.getAssignerId())) {
                throw new FieldsValidationException("Пользователь с ID " + filterDto.getAssignerId()
                        + " не зарегистрирован в системе");
            }
        }
        if (filterDto.getPage() != null && filterDto.getPage() < 1) {
            throw new FieldsValidationException("Индекс страницы не должен быть меньше единицы");
        }
        if (filterDto.getSize() != null && filterDto.getSize() < 1) {
            throw new FieldsValidationException("Размер страницы не должен быть меньше единицы");
        }
        if (filterDto.getSortBy() != null) {
            boolean isValidSortBy = Arrays.stream(TaskSortField.values())
                    .anyMatch(field -> field.getField().equals(filterDto.getSortBy()));
            if (!isValidSortBy) {
                throw new FieldsValidationException("Недопустимое поле сортировки: " + filterDto.getSortBy());
            }
        }
    }
}
