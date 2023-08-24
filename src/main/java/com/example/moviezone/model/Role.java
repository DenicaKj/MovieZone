package com.example.moviezone.model;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_USER, ROLE_ADMIN, ROLE_WORKER;

    @Override
    public String getAuthority() {
        return name();
    }
}
