package com.task_tracker.controllers.common;

import com.task_tracker.tasks_api.dto.TaskCreateDto;
import com.task_tracker.tasks_api.dto.TaskFilterDto;
import com.task_tracker.tasks_api.dto.TaskUpdateDto;
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

    private final UserRepository userRepository; //todo заменить на сервис

    public void validateCreateTask(final TaskCreateDto createDto) {
        validStatus(createDto.getStatus());
        validPriority(createDto.getPriority());
    }

    public void validateUpdateTask(final TaskUpdateDto updateDto) {
        if (updateDto.getStatus() != null) {
            validStatus(updateDto.getStatus());
        }
        if (updateDto.getPriority() != null) {
            validPriority(updateDto.getPriority());
        }
    }

    public void validateFilterTask(final TaskFilterDto filterDto) {
        if (filterDto.getStatus() != null) {
            validStatus(filterDto.getStatus());
        }
        if (filterDto.getPriority() != null) {
            validPriority(filterDto.getPriority());
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

    private void validStatus(final String status) {
        if (!EnumUtils.isValidEnumIgnoreCase(TaskStatus.class, status)) {
            throw new FieldsValidationException("Некорректное значение статуса: " + status);
        }
    }

    private void validPriority(final String priority) {
        if (!EnumUtils.isValidEnumIgnoreCase(TaskPriority.class, priority)) {
            throw new FieldsValidationException("Некорректное значение приоритета: " + priority);
        }
    }
}
