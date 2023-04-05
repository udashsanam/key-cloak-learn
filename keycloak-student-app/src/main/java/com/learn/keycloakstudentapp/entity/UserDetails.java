package com.learn.keycloakstudentapp.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private final String username;

    private final String password;

    private final Set<GrantedAuthority> grantedAuthoritySet;

    public UserDetails(String username, String password, Set<GrantedAuthority> grantedAuthoritySet) {
        this.username = username;
        this.password = password;
        this.grantedAuthoritySet = grantedAuthoritySet;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthoritySet;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
