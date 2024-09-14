package com.kamyczki.auth.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterUserDto {

    @NotNull
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
