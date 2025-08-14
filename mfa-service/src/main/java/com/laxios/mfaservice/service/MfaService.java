package com.laxios.mfaservice.service;

import com.laxios.mfaservice.dto.SetupRequest;
import com.laxios.mfaservice.dto.SetupResponse;

public interface MfaService {
    public SetupResponse setup(SetupRequest request);
}
