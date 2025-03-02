package com.task_tracker.tasks_api.mapper;

import com.task_tracker.comments_api.dto.CommentForTaskDto;
import com.task_tracker.comments_api.mapper.CommentMapper;
import com.task_tracker.comments_api.model.Comment;
import com.task_tracker.tasks_api.dto.TaskCreateDto;
import com.task_tracker.tasks_api.dto.TaskDto;
import com.task_tracker.tasks_api.dto.TaskUpdateDto;
import com.task_tracker.tasks_api.model.Task;
import com.task_tracker.technical.mapper.JsonNullableMapper;
import com.task_tracker.users_api.mapper.UserMapper;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {UserMapper.class,
                CommentMapper.class,
                JsonNullableMapper.class
        }
)
public abstract class TaskMapper {

    @Mapping(target = "createdBy", source = "createdById")
    @Mapping(target = "assigner", source = "assignerId")
    @Mapping(target = "comments", ignore = true)
    public abstract Task map(TaskCreateDto dto);

    @Mapping(target = "createdByInfo", source = "createdBy")
    @Mapping(target = "assignerInfo", source = "assigner")
    @Mapping(target = "commentsInfo",source = "comments")
    public abstract TaskDto map(Task model);

    @Mapping(target = "assigner", source = "assignerId")
    public abstract void update(TaskUpdateDto dto, @MappingTarget Task model);

    @Mapping(source = "user", target = "userInfo")
    public abstract CommentForTaskDto mapCommentToInfo(Comment comment);
}
