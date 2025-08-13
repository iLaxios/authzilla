package com.laxios.userservice.service;

import com.laxios.userservice.dto.LoginRequest;
import com.laxios.userservice.dto.LoginResponse;
import com.laxios.userservice.dto.RegisterRequest;
import com.laxios.userservice.dto.RegisterResponse;

public interface UserService {
    public RegisterResponse registerUser(RegisterRequest request);
    public LoginResponse loginUser(LoginRequest request);
}
