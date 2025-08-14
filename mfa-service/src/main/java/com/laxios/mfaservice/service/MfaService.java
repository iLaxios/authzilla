package com.laxios.mfaservice.service;

import com.laxios.mfaservice.dto.SetupRequest;
import com.laxios.mfaservice.dto.SetupResponse;
import com.laxios.mfaservice.dto.VerifyRequest;

public interface MfaService {
    public SetupResponse setup(SetupRequest request);

    public String verifyCode(VerifyRequest request);
}
