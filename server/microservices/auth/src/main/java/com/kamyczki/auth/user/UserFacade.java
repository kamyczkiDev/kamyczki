package com.kamyczki.auth.user;

import com.kamyczki.auth.user.dto.UserDetailsDto;

public interface UserFacade {

   UserDetailsDto getUserDetails(String username);
}
