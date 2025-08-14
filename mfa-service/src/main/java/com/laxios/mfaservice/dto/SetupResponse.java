package com.laxios.mfaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SetupResponse {

    private final String token;
    private final String userKey;
}
