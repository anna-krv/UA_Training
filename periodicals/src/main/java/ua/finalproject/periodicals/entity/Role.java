package ua.finalproject.periodicals.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;          // should not be reordered

    @Override
    public String getAuthority() {
        return name();
    }
}
