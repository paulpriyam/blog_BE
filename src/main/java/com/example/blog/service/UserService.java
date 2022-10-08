package com.example.blog.service;

import com.example.blog.entity.Users;
import com.example.blog.payload.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto user,Long userId);
    List<UserDto> getAllUsers();

    UserDto getUserById(Long userId);
    void deleteUserById(Long userId);
}
