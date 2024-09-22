package com.kamyczki.auth.user;

import com.kamyczki.auth.user.dto.RegisterUserDto;
import com.kamyczki.auth.user.dto.UserDetailsDto;
import com.kamyczki.auth.user.dto.UserDto;
import com.kamyczki.commons.error.ErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
class UserService implements UserFacade {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetailsDto getUserDetails(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUserDetails)
                .orElseThrow(() -> new ErrorException( "USER_NOT_FOUND","Username could not be found", NOT_FOUND));
    }

    UserDto registerUser(RegisterUserDto registerUserDto) {
        verifyUsername(registerUserDto.getUsername());
        verifyEmail(registerUserDto.getEmail());

        var encodedPassword = passwordEncoder.encode(registerUserDto.getPassword());
        var user = userMapper.toUser(registerUserDto, encodedPassword);
        var savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }

    //todo consider creating web binder validators?
    private void verifyEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ErrorException("USER_ALREADY_EXISTS", "User with this email already exists", BAD_REQUEST);
        }
    }

    private void verifyUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new ErrorException("USER_ALREADY_EXISTS", "User with this username already exists", BAD_REQUEST);
        }
    }
}
