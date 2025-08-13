package com.laxios.userservice.service;

import com.laxios.userservice.dto.RegisterRequest;
import com.laxios.userservice.dto.RegisterResponse;
import com.laxios.userservice.entity.User;

public interface UserService {
    public RegisterResponse registerUser(RegisterRequest request);
}
