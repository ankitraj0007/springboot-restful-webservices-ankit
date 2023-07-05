package com.learnwithankit.springbootrestfulwebservicesankit.service.impl;

import com.learnwithankit.springbootrestfulwebservicesankit.dto.UserDto;
import com.learnwithankit.springbootrestfulwebservicesankit.entity.User;
import com.learnwithankit.springbootrestfulwebservicesankit.exception.EmailAlreadyExistException;
import com.learnwithankit.springbootrestfulwebservicesankit.exception.ResourceNotFoundException;
import com.learnwithankit.springbootrestfulwebservicesankit.mapper.UserMapper;
import com.learnwithankit.springbootrestfulwebservicesankit.repository.UserRepository;
import com.learnwithankit.springbootrestfulwebservicesankit.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserSeviceImpl implements UserService {

//    @Autowired -> not required as because of @AllArgsConstructor i.e only one parameterized constructor, spring will automatically inject the dependency
    private UserRepository userRepository;
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistException("Email already exists for User");
        }
//        User user = UserMapper.mapToUser(userDto); //using custom user mapper class. not recommended
        User user = modelMapper.map(userDto, User.class); //using model mapper library

        User savedUser = userRepository.save(user);

//        UserDto savedUserDto = UserMapper.mapToUsetDto(savedUser);
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(long id) {
//        Optional<User> user = userRepository.findById(id);
        User user = userRepository.findById(id).orElseThrow(
                () ->  new ResourceNotFoundException("User","id",id)
        );
//        return UserMapper.mapToUsetDto(user.get());
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(UserMapper::mapToUsetDto).collect(Collectors.toList());
        return users.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
//        User userToBeUpdated = userRepository.findById(userDto.getId()).get();
        User userToBeUpdated = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id",userDto.getId())
        );
        userToBeUpdated.setFirstName(userDto.getFirstName());
        userToBeUpdated.setLastName(userDto.getLastName());
        userToBeUpdated.setEmail(userDto.getEmail());

        User updatedUser= userRepository.save(userToBeUpdated);
//        return UserMapper.mapToUsetDto(updatedUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
