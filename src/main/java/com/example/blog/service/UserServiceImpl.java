package com.example.blog.service;

import com.example.blog.entity.Users;
import com.example.blog.exemptions.ResourceNotFoundException;
import com.example.blog.payload.UserDto;
import com.example.blog.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        Users user = UserDtoToUser(userDto);
        Users savedUser = userRepo.save(user);
        return UsersToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        Users users = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        if (Objects.nonNull(userDto.getName()) && !"".equalsIgnoreCase(userDto.getName())) {
            users.setName(userDto.getName());
        }

        if (Objects.nonNull(userDto.getEmail()) && !"".equalsIgnoreCase(userDto.getEmail())) {
            users.setEmail(userDto.getEmail());
        }

        if (Objects.nonNull(userDto.getPassword()) && !"".equalsIgnoreCase(userDto.getPassword())) {
            users.setPassword(userDto.getPassword());
        }

        if (Objects.nonNull(userDto.getAbout()) && !"".equalsIgnoreCase(userDto.getAbout())) {
            users.setAbout(userDto.getAbout());
        }
        Users updatedUser = userRepo.save(users);
        return UsersToUserDto(updatedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<Users> allUsers = userRepo.findAll();
        return allUsers.stream().map(this::UsersToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long userId) {
        Users users = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return UsersToUserDto(users);
    }

    @Override
    public void deleteUserById(Long userId) {
        Users users = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        userRepo.delete(users);
    }

    private Users UserDtoToUser(UserDto userDto) {
//        Users users = new Users();
//        users.setName(userDto.getName());
//        users.setEmail(userDto.getEmail());
//        users.setAbout(userDto.getAbout());
//        users.setPassword(userDto.getPassword());
//        return users;
        return this.modelMapper.map(userDto, Users.class);
    }

    private UserDto UsersToUserDto(Users users) {
//        UserDto userDto = new UserDto();
//        userDto.setId(users.getId());
//        userDto.setAbout(users.getAbout());
//        userDto.setEmail(users.getEmail());
//        userDto.setName(users.getName());
//        userDto.setPassword(users.getPassword());
//        return userDto;
        return this.modelMapper.map(users, UserDto.class);
    }
}
