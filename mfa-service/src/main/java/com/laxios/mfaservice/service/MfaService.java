package com.laxios.mfaservice.service;

import com.laxios.mfaservice.dto.SetupRequest;

public interface MfaService {
    public void setup(SetupRequest request);
}
