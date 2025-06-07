package org.arai.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHasher {

    @Autowired
    private  PasswordEncoder passwordEncoder;

    /**
     * Hashes a raw password
     * @param rawPassword The plain text password to hash
     * @return The hashed password
     */
    public String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean matchesPassword(String plainPassword, String encryptedPassword) {
        return passwordEncoder.matches(plainPassword, encryptedPassword);
    }
}
