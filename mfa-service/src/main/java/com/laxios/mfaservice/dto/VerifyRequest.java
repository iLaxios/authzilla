package com.laxios.mfaservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VerifyRequest {

    @NotBlank
    private final String token;

    @NotBlank
    private final String code;
}
