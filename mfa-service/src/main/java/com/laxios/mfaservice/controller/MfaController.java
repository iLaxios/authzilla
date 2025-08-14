package com.laxios.mfaservice.controller;

import com.laxios.mfaservice.dto.SetupRequest;
import com.laxios.mfaservice.dto.SetupResponse;
import com.laxios.mfaservice.dto.VerifyRequest;
import com.laxios.mfaservice.service.MfaService;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mfa")
@Data
public class MfaController {

    private final MfaService mfaService;

    @PostMapping("/setup")
    SetupResponse setupMfa(@RequestBody SetupRequest request){
        return mfaService.setup(request);
    }

    @PostMapping("/verify")
    SetupResponse verifyCode(@RequestBody VerifyRequest request){
        return mfaService.verify(request);
    }
}
