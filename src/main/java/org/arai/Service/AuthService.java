package org.arai.Service;
import org.arai.crypto.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordHasher  passwordHasher;

    public String hashPassword(String rawPassword) {
        return passwordHasher.hashPassword(rawPassword);
    }

    public boolean  validarLogin(String user_password, String user_database_password) {
        return passwordHasher.matchesPassword(user_password, user_database_password);
    }



}
