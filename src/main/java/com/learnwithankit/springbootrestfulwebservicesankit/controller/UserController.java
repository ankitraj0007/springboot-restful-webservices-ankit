package com.learnwithankit.springbootrestfulwebservicesankit.controller;

import com.learnwithankit.springbootrestfulwebservicesankit.dto.UserDto;
import com.learnwithankit.springbootrestfulwebservicesankit.entity.User;
import com.learnwithankit.springbootrestfulwebservicesankit.exception.ErrorDetails;
import com.learnwithankit.springbootrestfulwebservicesankit.exception.ResourceNotFoundException;
import com.learnwithankit.springbootrestfulwebservicesankit.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
//for swagger openapi doc customization
@Tag(
        name = "CRUD rest api for user resource",
        description = "create, get, update and delete apis"
)
public class UserController {

    private UserService userService;

    //for swagger openapi doc customization related to create api
    @Operation(
            summary = "create User API",
            description = "learnwithankit create user API"
    )
    //related to swagger
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }

    @Operation(
            summary = "get user API",
            description = "learnwithankit get user API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP response 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") long userId){
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Operation(
            summary = "get all user API",
            description = "learnwithankit get all user rest API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP response 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @Operation(
            summary = "update user API",
            description = "learnwithankit update user API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP response 200 SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto, @PathVariable("id") long userId){
        userDto.setId(userId);
        UserDto updatedUser = userService.updateUser(userDto);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @Operation(
            summary = "delete user API",
            description = "learnwithankit delete user API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP response 200 SUCCESS"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User Successfully deleted",HttpStatus.OK);
    }

    //controller level exception handler
//    @ExceptionHandler(ResourceNotFoundException.class) // this is for controller level
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException
//            , WebRequest webRequest){
//
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                resourceNotFoundException.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//        );
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
}
