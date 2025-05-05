package com.sinnau.authapi.service;

import com.sinnau.authapi.model.SignupRequest;
import com.sinnau.authapi.model.SignupResponse;

public interface AuthService {

    public SignupResponse signup(SignupRequest signupRequest);
}
