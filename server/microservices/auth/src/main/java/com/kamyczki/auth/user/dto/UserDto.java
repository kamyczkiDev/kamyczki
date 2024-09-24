package com.kamyczki.auth.user.dto;

import com.kamyczki.auth.user.type.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class UserDto {

    private final long id;
    private final String username;
    private final Role role;
    private final String email;
}
