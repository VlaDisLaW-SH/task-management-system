package com.task_tracker.tasks_api.service;

import com.task_tracker.tasks_api.dto.*;
import com.task_tracker.tasks_api.enumeration.TaskPriority;
import com.task_tracker.tasks_api.enumeration.TaskSortField;
import com.task_tracker.tasks_api.enumeration.TaskStatus;
import com.task_tracker.tasks_api.mapper.TaskMapper;
import com.task_tracker.tasks_api.repository.TaskRepository;
import com.task_tracker.technical.exception.FieldsValidationException;
import com.task_tracker.technical.exception.ResourceNotFoundException;
import com.task_tracker.users_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    private final UserRepository userRepository;

    public TaskEnvelopDto getTasks(int page, int size, String sort) {
        var pageRequest = PageRequest.of(page -1, size, Sort.by(sort));
        var taskPage = taskRepository.findAll(pageRequest);
        var taskDto = taskPage.stream()
                .map(taskMapper::map)
                .toList();
        return new TaskEnvelopDto(
                taskDto,
                taskPage.getTotalElements(),
                taskPage.getTotalPages()
        );
    }

    public TaskDto findById(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " no found"));
        return taskMapper.map(task);
    }

    @Transactional
    public TaskDto create(TaskCreateDto taskDto) {
        var task = taskMapper.map(taskDto);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    public TaskDto update(TaskUpdateDto taskDto, Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " no found"));
        taskMapper.update(taskDto, task);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    public void delete(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " no found"));
        taskRepository.delete(task);
    }

    public TaskEnvelopDto filterTasks(TaskFilterDto filter) {

        if (filter.getPage() == null) {
            filter.setPage(1);
        }
        if (filter.getSize() == null) {
            filter.setSize(10);
        }
        if (filter.getSortBy() == null || filter.getSortBy().isEmpty()) {
            filter.setSortBy("id");
        }

        TaskStatus taskStatus = null;
        TaskPriority taskPriority = null;

        if (filter.getStatus() != null) {
            taskStatus = TaskStatus.valueOf(filter.getStatus().toUpperCase());
        }
        if (filter.getPriority() != null) {
            taskPriority = TaskPriority.valueOf(filter.getPriority().toUpperCase());
        }

        Pageable pageable = PageRequest.of(
                filter.getPage() - 1,
                filter.getSize(),
                Sort.by(filter.getSortBy())
        );
        var taskPage = taskRepository.findByFilters(
                taskStatus,
                taskPriority,
                filter.getCreatedById(),
                filter.getAssignerId(),
                pageable);
        var taskDto = taskPage.stream()
                .map(taskMapper::map)
                .toList();
        return new TaskEnvelopDto(
                taskDto,
                taskPage.getTotalElements(),
                taskPage.getTotalPages()
        );
    }

    public void setAssigner(TaskSetAssignerDto assignerDto) {
        var task = taskRepository.findById(assignerDto.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + assignerDto.getTaskId()
                        + " no found"));
        var assigner = userRepository.findById(assignerDto.getAssignerId())
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + assignerDto.getAssignerId()
                        + " no found"));
        if (task.getAssigner() != null) {
            task.getAssigner().removeTaskFromListAssigner(task);
        }
        task.setAssigner(assigner);
        assigner.addTaskInListAssigner(task);
        taskRepository.save(task);
    }

    public void removeAssigner(Long taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + taskId + " no found"));
        if (task.getAssigner() == null) {
            throw new ResourceNotFoundException("У задачи с ID " + taskId + " отсутствует исполнитель");
        }
        task.getAssigner().removeTaskFromListAssigner(task);
        task.setAssigner(null);
        taskRepository.save(task);
    }
}
