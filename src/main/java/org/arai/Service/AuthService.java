package org.arai.Service;
import org.arai.crypto.PasswordHasher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordHasher  passwordHasher;


    private Logger logger = LoggerFactory.getLogger(AuthService.class);





    /**
     * hashea el password pasado como string
     * @param rawPassword
     * @return
     */
    public String hashPassword(String rawPassword) {
        logger.info("hashing password!!");
        return passwordHasher.hashPassword(rawPassword);
    }

    /**
     *
     * @param user_password
     * @param user_database_password
     * @return
     */

    public boolean  validarLogin(String user_password, String user_database_password) {
        logger.info("Validando login!!");
        return passwordHasher.matchesPassword(user_password, user_database_password);
    }


}
