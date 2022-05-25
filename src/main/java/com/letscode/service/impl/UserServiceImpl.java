package com.letscode.service.impl;

import com.letscode.domain.model.User;
import com.letscode.domain.repository.UserRepository;
import com.letscode.dto.UserDto;
import com.letscode.exception.UserNotFoundException;
import com.letscode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository repository;

    @Override
    public void saveUser(final UserDto userDto) {
        User userEntity = new User(userDto.getUsername(), userDto.getName());
        repository.save(userEntity);
    }

    @Override
    public List<UserDto> getAll() {
        return
                repository
                        .findAll()
                        .stream()
                        .map(user -> new UserDto(user.getUsername(), user.getName()))
                        .collect(Collectors.toList());
    }

    @Override
    public User findUserByUsername(String username) throws UserNotFoundException {
        return repository
                .findById(username)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }
}
