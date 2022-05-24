package com.letscode.service;

import com.letscode.dto.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    List<UserDto> getAll();
}
