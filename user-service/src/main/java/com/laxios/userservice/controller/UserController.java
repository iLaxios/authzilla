package com.laxios.userservice.controller;


import com.laxios.userservice.dto.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterRequest registerUser(@Valid @RequestBody RegisterRequest request) {
        return request;
    }
}
