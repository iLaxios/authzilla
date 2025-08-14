package com.laxios.mfaservice.service.impl;

import com.laxios.mfaservice.dto.SetupRequest;
import com.laxios.mfaservice.service.MfaService;
import com.laxios.mfaservice.util.JwtUtil;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class MfaServiceImpl implements MfaService {

    private final JwtUtil jwtUtil;

    public void setup(SetupRequest request) {

        String token = request.getToken();

        if(!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("Invalid token");
        }
        
        System.out.println(request.getToken());
    }
}

