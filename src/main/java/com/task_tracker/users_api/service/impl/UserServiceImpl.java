package com.task_tracker.users_api.service.impl;

import com.task_tracker.technical.exception.ResourceNotFoundException;
import com.task_tracker.users_api.dto.UserCreateDto;
import com.task_tracker.users_api.dto.UserDto;
import com.task_tracker.users_api.dto.UserEnvelopDto;
import com.task_tracker.users_api.exception.DuplicateEmailException;
import com.task_tracker.users_api.mapper.UserMapper;
import com.task_tracker.users_api.repository.UserRepository;
import com.task_tracker.users_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserEnvelopDto getUsers(int page, int size, String sort) {
        var pageRequest = PageRequest.of(page - 1, size, Sort.by(sort));
        var userPage = userRepository.findAll(pageRequest);
        var userDto = userPage.stream()
                .map(userMapper::map)
                .toList();
        return new UserEnvelopDto(
                userDto,
                userPage.getTotalElements(),
                userPage.getTotalPages()
        );
    }

    @Override
    public UserDto findById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " no found"));
        return userMapper.map(user);
    }

    @Override
    public UserDto create(UserCreateDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new DuplicateEmailException("Email уже используется");
        }
        var user = userMapper.map(userDto);
        userRepository.save(user);
        return userMapper.map(user);
    }

    @Override
    public void delete(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " no found"));
        userRepository.delete(user);
    }
}
