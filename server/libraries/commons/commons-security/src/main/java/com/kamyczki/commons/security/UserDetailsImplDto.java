package com.kamyczki.commons.security;

public class UserDetailsImplDto implements UserDetails {

    private String username;

    public UserDetailsImplDto() {
    }

    public UserDetailsImplDto( String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
