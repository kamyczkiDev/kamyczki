package com.kamyczki.auth.authentication;

import com.kamyczki.auth.authentication.dto.SignInDto;
import com.kamyczki.auth.authentication.dto.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("sign-in")
    TokenDto authenticate(@RequestBody @Valid SignInDto signInDto) {
        return service.authenticate(signInDto);
    }
}
