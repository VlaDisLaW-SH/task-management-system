package com.task_tracker.controllers;

import com.task_tracker.tasks_api.dto.*;
import com.task_tracker.tasks_api.service.TaskService;
import com.task_tracker.technical.exception.CustomValidationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TaskService taskService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TaskEnvelopDto> index(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        TaskEnvelopDto taskEnvelopDto = taskService.getTasks(page, size, sort);
        return ResponseEntity.ok(taskEnvelopDto);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TaskDto> show(@PathVariable Long id) {
        var task = taskService.findById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TaskDto> create(
            @Valid @RequestBody TaskCreateDto taskData,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult);
        }
        var task = taskService.create(taskData);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(task);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TaskDto> update(
            @Valid @RequestBody TaskUpdateDto taskData,
            BindingResult bindingResult,
            @PathVariable Long id
    ) {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult);
        }
        var task = taskService.update(taskData, id);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable Long id) {
        taskService.delete(id);
    }

    @PostMapping(path = "/filter")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TaskEnvelopDto> filterTasks(
            @Valid @RequestBody TaskFilterDto filterDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult);
        }
        var taskEnvelopDto = taskService.filterTasks(filterDto);
        return ResponseEntity.ok(taskEnvelopDto);
    }
}
