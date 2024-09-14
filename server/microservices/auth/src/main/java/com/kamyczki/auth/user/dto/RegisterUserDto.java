package com.kamyczki.auth.user.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterUserDto {

    @NotNull
    @NotBlank
    @Max(50)
    private final String username;

    @NotNull
    @Min(10)
    @Max(100)
    private final String password;

    @NotNull
    @Email
    @Max(100)
    private final String email;
}
