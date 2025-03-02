package com.task_tracker.users_api.mapper;

import com.task_tracker.comments_api.mapper.CommentMapper;
import com.task_tracker.tasks_api.model.Task;
import com.task_tracker.technical.exception.ResourceNotFoundException;
import com.task_tracker.users_api.dto.UserBriefInfoDto;
import com.task_tracker.users_api.dto.UserCreateDto;
import com.task_tracker.users_api.dto.UserDto;
import com.task_tracker.users_api.model.User;
import com.task_tracker.users_api.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {CommentMapper.class}
)
public abstract class UserMapper {

    @Autowired
    private UserRepository userRepository;
    public abstract User map(UserCreateDto dto);

    @Mapping(target = "listIdTasksCreatedBy", source = "listTasksCreatedBy")
    @Mapping(target = "listIdTasksPerformer", source = "listTasksAssigner")
    public abstract UserDto map(User model);

    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "email", target = "email")
    public abstract UserBriefInfoDto mapUserToBriefInfo(User user);

    public User mapIdToUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " no found"));
    }

    public List<Long> mapTasksToListId(List<Task> tasks) {
        return tasks.stream()
                .map(Task::getId)
                .toList();
    }
}
