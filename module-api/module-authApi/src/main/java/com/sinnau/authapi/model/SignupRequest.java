package com.sinnau.authapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SignupRequest {
    private String name;
    private String password;
    private String email;
}
