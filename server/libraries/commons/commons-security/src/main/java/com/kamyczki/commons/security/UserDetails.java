package com.kamyczki.commons.security;

public interface UserDetails {

    String getUsername();

    default boolean isAccountNonExpired() {
        return true;
    }

    default boolean isAccountNonLocked() {
        return true;
    }

    default boolean isCredentialsNonExpired() {
        return true;
    }

    default boolean isEnabled() {
        return true;
    }
}
