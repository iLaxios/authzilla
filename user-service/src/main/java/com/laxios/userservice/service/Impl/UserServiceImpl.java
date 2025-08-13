package com.laxios.userservice.service.Impl;

import com.laxios.userservice.dto.RegisterRequest;
import com.laxios.userservice.dto.RegisterResponse;
import com.laxios.userservice.entity.User;
import com.laxios.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public RegisterResponse registerUser(RegisterRequest request) {
        User user = User.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        return new RegisterResponse(user.getId(), user.getName());
    }
}
