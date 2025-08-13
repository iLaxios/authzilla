package com.laxios.userservice.service.Impl;

import com.laxios.userservice.dto.RegisterRequest;
import com.laxios.userservice.dto.RegisterResponse;
import com.laxios.userservice.entity.User;
import com.laxios.userservice.repository.UserRepository;
import com.laxios.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public RegisterResponse registerUser(RegisterRequest request) {

        if(userRepository.existsByName(request.getName())) {
            throw  new IllegalArgumentException("Username already taken");
        }

        User user = User.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return new RegisterResponse(user.getId(), user.getName());
    }
}
