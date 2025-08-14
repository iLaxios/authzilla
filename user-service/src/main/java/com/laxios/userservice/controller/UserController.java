package com.laxios.userservice.controller;


import com.laxios.userservice.dto.*;
import com.laxios.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse registerUser(@Valid @RequestBody RegisterRequest request) {

        RegisterResponse response = userService.registerUser(request);
        return response;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse loginUser(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = userService.loginUser(request);
        return response;
    }

    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    public String verifyJwt(@Valid @RequestBody JwtVerifyRequest request) {
        return userService.verifyToken(request);
    }
}
