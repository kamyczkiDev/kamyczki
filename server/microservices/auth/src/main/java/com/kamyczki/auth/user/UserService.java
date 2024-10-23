package com.kamyczki.auth.user;

import com.kamyczki.auth.user.dto.RegisterUserDto;
import com.kamyczki.auth.user.dto.UserDetailsDto;
import com.kamyczki.auth.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.kamyczki.commons.error.ErrorCodes.RESOURCE_ALREADY_EXISTS;
import static com.kamyczki.commons.error.ErrorCodes.RESOURCE_NOT_FOUND;


@Service
@RequiredArgsConstructor
class UserService implements UserFacade {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetailsDto getUserDetails(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUserDetails)
                .orElseThrow(()->  RESOURCE_NOT_FOUND.throwWithObjectAndFieldAndValue("User", "username", username));
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
            throw RESOURCE_ALREADY_EXISTS.throwWithObjectAndField("User", "email");
        }
    }

    private void verifyUsername(String username) {
        if (userRepository.existsByUsername(username)) {
           throw RESOURCE_ALREADY_EXISTS.throwWithObjectAndField("User", "username");
        }
    }
}
