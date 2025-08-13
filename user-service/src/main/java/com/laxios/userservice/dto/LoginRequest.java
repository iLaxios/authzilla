package com.laxios.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class LoginRequest {

    @NotBlank(message = "username is required")
    private String name;

    @NotBlank(message = "password is required")
    private String password;
}
