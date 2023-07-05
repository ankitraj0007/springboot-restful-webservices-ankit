package com.learnwithankit.springbootrestfulwebservicesankit.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//for swagger
@Schema(
        description = "USER DTO Model"
)
public class UserDto {

    //don't keep any sensitive data field in dto layer. otherwise all fields are usually same as entity

    private long id;

    @Schema(description = "User first name") //for swagger
    @NotEmpty(message = "User first name should not be null or empty")
    private String firstName;

    @Schema(description = "User last name")
    @NotEmpty(message = "User last name should not be null or empty")
    private String lastName;

    @Schema(description = "User email")
    @NotEmpty(message = "User email should not be null or empty")
    @Email(message = "User email should be valid")
    private String email;

}
