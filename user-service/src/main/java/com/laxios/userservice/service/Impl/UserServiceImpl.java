package com.laxios.userservice.service.Impl;

import com.laxios.userservice.dto.*;
import com.laxios.userservice.entity.User;
import com.laxios.userservice.repository.UserRepository;
import com.laxios.userservice.service.UserService;
import com.laxios.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
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

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {

        Optional<User> userOptional = userRepository.findByName(loginRequest.getName());

        if(userOptional.isEmpty()) {
            throw  new IllegalArgumentException("Username or password is wrong!");
        }

        User user = userOptional.get();
        String userPassword = user.getPassword();

        if(!passwordEncoder.matches(loginRequest.getPassword(), userPassword)) {
            throw  new IllegalArgumentException("Username or password is wrong!");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getName());
        return new LoginResponse(user.getId(), user.getName(), token);
    }

    public String verifyToken(JwtVerifyRequest request) {

        String token = request.getToken();

        if(!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("Invalid token");
        }

        return "Token verified successfully!";
    }
}
