package com.example.security.authentication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String confirmPassword;
    private String role;

    public Role getAuthorities() {
        if (role.equalsIgnoreCase("admin")) {
            return Role.ADMIN;
        } else {
            return Role.USER;
        }
    }
}