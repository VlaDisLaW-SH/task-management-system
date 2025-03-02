package com.task_tracker.comments_api.mapper;

import com.task_tracker.comments_api.dto.*;
import com.task_tracker.comments_api.model.Comment;
import com.task_tracker.users_api.mapper.UserMapper;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {UserMapper.class}
)
public abstract class CommentMapper {

    @Mapping(source = "taskId", target = "task.id")
    @Mapping(source = "userId", target = "user.id")
    public abstract Comment map(CommentCreateDto dto);

    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "user.id", target = "userId")
    public abstract CommentDto map(Comment model);

    public abstract void update(CommentUpdateDto dto, @MappingTarget Comment model);

    @Mapping(source = "task.id", target = "taskId")
    public abstract CommentForUserDto mapCommentToUser(Comment model);
}
