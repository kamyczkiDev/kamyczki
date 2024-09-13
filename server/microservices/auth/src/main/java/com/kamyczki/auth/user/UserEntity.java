package com.kamyczki.auth.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static org.hibernate.cfg.AvailableSettings.USER;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USER")
public class UserEntity implements UserDetails {

    @Id
    private long id;

    private String name;
    private String surname;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(USER));
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return true;
    }
}
