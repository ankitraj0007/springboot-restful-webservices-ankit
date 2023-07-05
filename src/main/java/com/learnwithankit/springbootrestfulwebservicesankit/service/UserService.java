package com.learnwithankit.springbootrestfulwebservicesankit.service;

import com.learnwithankit.springbootrestfulwebservicesankit.dto.UserDto;
import com.learnwithankit.springbootrestfulwebservicesankit.entity.User;

import java.util.List;

public interface UserService {

    public UserDto createUser(UserDto userDto);

    public UserDto getUserById(long id);

    public List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);

    void deleteUser(long id);
}
