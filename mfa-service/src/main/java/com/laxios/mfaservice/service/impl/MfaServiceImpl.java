package com.laxios.mfaservice.service.impl;

import com.laxios.mfaservice.dto.SetupRequest;
import com.laxios.mfaservice.service.MfaService;
import org.springframework.stereotype.Service;

@Service
public class MfaServiceImpl implements MfaService {
    public void setup(SetupRequest request) {
        System.out.println(request.getToken());
    }
}

