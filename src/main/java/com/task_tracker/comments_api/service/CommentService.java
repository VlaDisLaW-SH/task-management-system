package com.task_tracker.comments_api.service;

import com.task_tracker.comments_api.dto.CommentCreateDto;
import com.task_tracker.comments_api.dto.CommentDto;
import com.task_tracker.comments_api.dto.CommentEnvelopDto;
import com.task_tracker.comments_api.dto.CommentUpdateDto;
import com.task_tracker.comments_api.mapper.CommentMapper;
import com.task_tracker.comments_api.repository.CommentRepository;
import com.task_tracker.technical.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    public CommentEnvelopDto getComments(int page, int size, String sort) {
        var pageRequest = PageRequest.of(page -1, size, Sort.by(sort));
        var commentPage = commentRepository.findAll(pageRequest);
        var commentDto = commentPage.stream()
                .map(commentMapper::map)
                .toList();
        return new CommentEnvelopDto(
                commentDto,
                commentPage.getTotalElements(),
                commentPage.getTotalPages()
        );
    }

    public CommentDto findById(Long id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " no found"));
        return commentMapper.map(comment);
    }

    public CommentDto create(CommentCreateDto commentDTO) {
        var comment = commentMapper.map(commentDTO);
        commentRepository.save(comment);
        return commentMapper.map(comment);
    }

    public CommentDto update(CommentUpdateDto commentDto, Long id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " no found"));
        commentMapper.update(commentDto, comment);
        commentRepository.save(comment);
        return commentMapper.map(comment);
    }

    public void delete(Long id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " no found"));
        commentRepository.delete(comment);
    }
}
