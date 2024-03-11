package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterDto {
    @NotNull(message = "Username cannot be null")
    @Size(min = 3, message = "Username must be at least 3 characters long")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$&*]).+$", message = "Password must contain at least one uppercase letter and one special character")
    private String password;
}
