package com.kamyczki.commons.security;

public class UserDetailsImplDto implements UserDetails {

    private String password;
    private String username;

    public UserDetailsImplDto() {
    }

    public UserDetailsImplDto(String password, String username) {
        this.password = password;
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
