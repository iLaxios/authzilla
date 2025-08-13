package com.laxios.userservice.controller;


import com.laxios.userservice.dto.RegisterRequest;
import com.laxios.userservice.dto.RegisterResponse;
import com.laxios.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
}
