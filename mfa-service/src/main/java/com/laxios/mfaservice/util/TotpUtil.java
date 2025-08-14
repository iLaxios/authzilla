package com.laxios.mfaservice.util;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Data
public class TotpUtil {

    private static final Duration TIME_STEP = Duration.ofSeconds(30); // TOTP step interval
    private static final int DIGITS = 6; // 6-digit code

    // Generate a secret key for the user
    public static String generateSecret() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA1");
        keyGen.init(160); // 160-bit key
        SecretKey key = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    // Verify TOTP token
    public static boolean verifyToken(String base64Secret, String token) throws Exception {
        byte[] secretBytes = Base64.getDecoder().decode(base64Secret);
        SecretKey key = new javax.crypto.spec.SecretKeySpec(secretBytes, "HmacSHA1");

        TimeBasedOneTimePasswordGenerator totp = new TimeBasedOneTimePasswordGenerator(TIME_STEP, DIGITS);
        int generated = totp.generateOneTimePassword(key, Instant.now());
        return Integer.parseInt(token) == generated;
    }
}

