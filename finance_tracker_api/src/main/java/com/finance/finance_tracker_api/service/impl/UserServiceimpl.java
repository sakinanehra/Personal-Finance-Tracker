
package com.finance.finance_tracker_api.service.impl;

import com.finance.finance_tracker_api.dto.request.UserRequest;
import com.finance.finance_tracker_api.dto.response.UserResponse;
import com.finance.finance_tracker_api.entity.User;
import com.finance.finance_tracker_api.exception.ResourceNotFoundException;
import com.finance.finance_tracker_api.mapper.UserMapper;
import com.finance.finance_tracker_api.repository.UserRepository;
import com.finance.finance_tracker_api.service.interfaces.UserServiceint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl implements UserServiceint {

    private final UserRepository userRepository;

    public UserServiceimpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(UserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = UserMapper.toEntity(request);

        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id : " + id));

        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponse updateUser(Integer id, UserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id : " + id));

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User updatedUser = userRepository.save(user);

        return UserMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id : " + id));

        userRepository.delete(user);
    }
}