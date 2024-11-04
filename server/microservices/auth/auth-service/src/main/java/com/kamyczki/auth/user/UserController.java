package com.kamyczki.auth.user;

import com.kamyczki.auth.user.dto.RegisterUserDto;
import com.kamyczki.auth.user.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@Tag(name = "Users")
class UserController {

    private final UserService userService;

    @PostMapping("register")
    UserDto register(@RequestBody @Valid RegisterUserDto registerUserDto) {
        return userService.registerUser(registerUserDto);
    }
}
