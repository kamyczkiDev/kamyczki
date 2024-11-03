package com.kamyczki.auth.user;

import com.kamyczki.auth.user.dto.RegisterUserDto;
import com.kamyczki.auth.user.dto.UserDetailsDto;
import com.kamyczki.auth.user.dto.UserDto;
import com.kamyczki.auth.user.type.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface UserMapper {

    @Mapping(target = "password", source = "encodedPassword")
    @Mapping(target = "role", expression = "java(getRoleUser())")
    User toUser(RegisterUserDto registerUserDto, String encodedPassword);

    UserDto toUserDto(User user);

    UserDetailsDto toUserDetails(User user);

    default Role getRoleUser(){
        return Role.USER;
    }
}
