package com.example.project3.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    @NotEmpty(message = "username cannot be empty")
    @Size(min = 4, max = 10,message = "username should be between 4 and 10")
    private String username;

    @NotEmpty(message = "password cannot be empty")
    @Pattern(regexp = "^.{6,}$",message = "minimum password requirement is 6 characters")
    private String password;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 2, max = 20, message = "name should be between 2 and 20")
    private String name;

    @Email
    private String email;

    @NotEmpty(message = "phone number cannot be empty")
    @Pattern(regexp = "^05\\d{8}$",message = "phone number should start with 05")
    private String phoneNumber;
}
