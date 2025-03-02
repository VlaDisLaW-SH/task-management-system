package com.task_tracker.controllers;

import com.task_tracker.comments_api.dto.CommentCreateDto;
import com.task_tracker.comments_api.dto.CommentDto;
import com.task_tracker.comments_api.dto.CommentEnvelopDto;
import com.task_tracker.comments_api.dto.CommentUpdateDto;
import com.task_tracker.comments_api.service.CommentService;
import com.task_tracker.technical.exception.CustomValidationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentService commentService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CommentEnvelopDto> index(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        CommentEnvelopDto commentEnvelopDto = commentService.getComments(page, size, sort);
        return ResponseEntity.ok(commentEnvelopDto);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CommentDto> show(@PathVariable Long id) {
        var comment = commentService.findById(id);
        return ResponseEntity.ok(comment);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CommentDto> create(
            @Valid @RequestBody CommentCreateDto commentData,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult);
        }
        var comment = commentService.create(commentData);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(comment);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CommentDto> update(
            @Valid @RequestBody CommentUpdateDto commentData,
            BindingResult bindingResult,
            @PathVariable Long id
    ) {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult);
        }
        var comment = commentService.update(commentData, id);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable Long id) {
        commentService.delete(id);
    }
}
