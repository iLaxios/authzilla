package com.laxios.mfaservice.service.impl;

import com.laxios.mfaservice.dto.SetupRequest;
import com.laxios.mfaservice.dto.SetupResponse;
import com.laxios.mfaservice.entity.User;
import com.laxios.mfaservice.repository.UserRepository;
import com.laxios.mfaservice.service.MfaService;
import com.laxios.mfaservice.util.JwtUtil;
import com.laxios.mfaservice.util.TotpUtil;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Data
public class MfaServiceImpl implements MfaService {

    private final JwtUtil jwtUtil;
    private final TotpUtil totpUtil;
    private final UserRepository userRepository;

    public SetupResponse setup(SetupRequest request) {

        String token = request.getToken();

        if(!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("Invalid token");
        }

        String userKey = "";
        try {
            userKey = totpUtil.generateSecret();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String username = jwtUtil.getClaim(token, "username", String.class);
        String userIdStr = jwtUtil.getClaim(token, "sub", String.class);
        UUID userId = UUID.fromString(userIdStr);

        userRepository.save(new User(
                userId, userKey, username
        ));

        String newToken = jwtUtil.generateToken(userId, username);
        return new SetupResponse(newToken, userKey);
    }
}

