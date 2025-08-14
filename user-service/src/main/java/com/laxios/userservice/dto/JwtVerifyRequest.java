package com.laxios.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JwtVerifyRequest {

    @NotBlank(message="jwt is empty")
    private String token;
}
