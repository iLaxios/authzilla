package com.laxios.mfaservice.util;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

import lombok.Data;
import org.apache.commons.codec.binary.Base32;
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
    public boolean verifyToken(String base32Secret, String token) throws Exception {
        byte[] secretBytes = new Base32().decode(base32Secret); // Base32 decoding
        SecretKey key = new SecretKeySpec(secretBytes, "HmacSHA1");

        TimeBasedOneTimePasswordGenerator totp =
                new TimeBasedOneTimePasswordGenerator(Duration.ofSeconds(timeStep), digits);

        int inputCode;
        try {
            inputCode = Integer.parseInt(token);
        } catch (NumberFormatException e) {
            return false;
        }

        // Allow clock drift: previous, current, next
        for (int offset = -1; offset <= 1; offset++) {
            Instant time = Instant.now().plusSeconds(timeStep * offset);
            int generated = totp.generateOneTimePassword(key, time);
            if (inputCode == generated) {
                return true;
            }
        }
        return false;
    }
}

