package com.sinnau.domain.user.model;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    private Set<Role> roles;
}