package com.laxios.userservice.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "Username is required")
    private String name;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
