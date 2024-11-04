package com.kamyczki.auth.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class SignInDto {

    @NotNull
    @NotBlank
    private final String username;

    @NotNull
    @NotBlank
    private final String password;
}
