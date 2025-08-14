package com.laxios.userservice.service;

import com.laxios.userservice.dto.*;

public interface UserService {
    public RegisterResponse registerUser(RegisterRequest request);
    public LoginResponse loginUser(LoginRequest request);
    public String verifyToken(JwtVerifyRequest token);
}
