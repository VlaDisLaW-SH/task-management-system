package com.task_tracker.tasks_api.service;

import com.task_tracker.comments_api.dto.CommentCreateDto;
import com.task_tracker.comments_api.dto.CommentForTaskCreateDto;
import com.task_tracker.comments_api.mapper.CommentMapper;
import com.task_tracker.comments_api.repository.CommentRepository;
import com.task_tracker.tasks_api.dto.*;
import com.task_tracker.tasks_api.mapper.TaskMapper;
import com.task_tracker.tasks_api.model.Task;
import com.task_tracker.tasks_api.repository.TaskRepository;
import com.task_tracker.technical.exception.FieldsValidationException;
import com.task_tracker.technical.exception.ResourceNotFoundException;
import com.task_tracker.users_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    private final CommentMapper commentMapper;

    private final CommentRepository commentRepository;

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

        for (CommentForTaskCreateDto commentForTaskCreateDto: taskDto.getComments()) {
            if (commentForTaskCreateDto.getBody().isEmpty()) {
                throw new FieldsValidationException("Поле Body не должно быть пустым");
            }
            var commentDto = new CommentCreateDto();
            commentDto.setBody(commentForTaskCreateDto.getBody());
            commentDto.setTaskId(task.getId());
            commentDto.setUserId(task.getCreatedBy().getId());
            var comment = commentMapper.map(commentDto);
            comment.setUser(task.getCreatedBy());
            commentRepository.save(comment);
            task.setCommentToList(comment);
        }
        return taskMapper.map(task);
    }

    public TaskDto update(TaskUpdateDto taskDto, Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " no found"));
        if (task.getAssigner() == null) {
            if (taskDto.getAssignerId() == null) {
                task.setAssigner(null);
            }
            if (taskDto.getAssignerId() != null) {
                setAssignerForUpdate(task, taskDto.getAssignerId());
            }
        }
        if (task.getAssigner() != null) {
            if (taskDto.getAssignerId() == null) {
                taskDto.setAssignerId(task.getAssigner().getId());
                setAssignerForUpdate(task, taskDto.getAssignerId());
            }
            if (!Objects.equals(task.getAssigner().getId(), taskDto.getAssignerId())) {
                setAssignerForUpdate(task, taskDto.getAssignerId());
            }
        }
        taskMapper.update(taskDto, task);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    public void delete(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " no found"));
        taskRepository.delete(task);
    }

    public List<TaskDto> filterTasks(TaskFilterDto filter) {
        var createdById = filter.getCreatedById();
        var assignerId = filter.getAssignerId();

        if (createdById != null) {
            if (!userRepository.existsById(createdById)) {
                throw new FieldsValidationException("Пользователь с ID " + createdById + " не зарегистрирован в системе");
            }
        }
        if (assignerId != null) {
            if (!userRepository.existsById(assignerId)) {
                throw new FieldsValidationException("Пользователь с ID " + assignerId + " не зарегистрирован в системе");
            }
        }
        return taskRepository.findByFilters(filter)
                .stream()
                .map(taskMapper::map)
                .toList();
    }

    private void setAssignerForUpdate(Task task, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id "
                        + userId + " no found"));
        task.setAssigner(user);
    }
}
