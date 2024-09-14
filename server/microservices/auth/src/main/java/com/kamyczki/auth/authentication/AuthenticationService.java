package com.kamyczki.auth.authentication;


import com.kamyczki.auth.authentication.dto.SignInDto;
import com.kamyczki.auth.authentication.dto.TokenDto;
import com.kamyczki.auth.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AuthenticationService {

    private final UserFacade userFacade;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public TokenDto authenticate(SignInDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userFacade.getUserDetails(request.getUsername());
        var jwtToken = jwtService.generateToken(user);
        return new TokenDto(jwtToken);
    }
}
