package com.laxios.mfaservice.util;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class TotpUtil {

    @Value("${totp.time-step}")
    private int timeStep; // TOTP step interval

    @Value("${totp.code-length}")
    private int digits;

    @Value("${totp.key-length}")
    private int keyLength;

    // Generate a secret key for the user
    private static final String BASE32_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
    private static final SecureRandom random = new SecureRandom();

    public String generateSecret() {
        StringBuilder sb = new StringBuilder(keyLength);
        for (int i = 0; i < keyLength; i++) {
            int index = random.nextInt(BASE32_CHARS.length());
            sb.append(BASE32_CHARS.charAt(index));
        }
        return sb.toString();
    }

    // Verify TOTP token
    public  boolean verifyToken(String base64Secret, String token) throws Exception {
        byte[] secretBytes = Base64.getDecoder().decode(base64Secret);
        SecretKey key = new javax.crypto.spec.SecretKeySpec(secretBytes, "HmacSHA1");

        TimeBasedOneTimePasswordGenerator totp = new TimeBasedOneTimePasswordGenerator(Duration.ofSeconds(timeStep), digits);
        int generated = totp.generateOneTimePassword(key, Instant.now());
        return Integer.parseInt(token) == generated;
    }
}

