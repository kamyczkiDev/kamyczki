package com.kamyczki.auth.authentication;

import com.kamyczki.auth.authentication.dto.SignInDto;
import com.kamyczki.auth.feign.AuthFeignClient;
import com.kamyczki.auth.feign.TokenDto;
import com.kamyczki.commons.security.UserDetailsImplDto;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authentication")
class AuthenticationController implements AuthFeignClient {

    private final AuthenticationService service;

    @PostMapping("sign-in")
    TokenDto authenticate(@RequestBody @Valid SignInDto signInDto) {
        return service.authenticate(signInDto);
    }

    @Override
    @PostMapping("validate-token")
    public UserDetailsImplDto validateToken(@RequestBody String token) {
        return service.validateToken(token);
    }
}
