package com.kamyczki.auth.user.dto;

import com.kamyczki.auth.user.type.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserDto {

    private final long id;
    private final String username;
    private final Role role;
    private final String email;
}
