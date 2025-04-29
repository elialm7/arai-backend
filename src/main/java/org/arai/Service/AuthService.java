package org.arai.Service;

import org.arai.Entities.Usuario;
import org.arai.Exceptions.UsuarioNoEncontradoException;
import org.arai.Model.LoginRequestDTO;
import org.arai.Persistence.UsuarioRepository;
import org.arai.crypto.PasswordHasher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private PasswordHasher  passwordHasher;

    @Autowired
    private UsuarioRepository usuarioRepository;


    private Logger logger = LoggerFactory.getLogger(AuthService.class);





    /**
     * hashea el password pasado como string
     * @param rawPassword
     * @return
     */
    public String hashPassword(String rawPassword) {
        return passwordHasher.hashPassword(rawPassword);
    }

    /**
     * verifica que el password sin hashear sea igual al del que esta almacenado
     * @param rawPassword
     * @param hashedPassword
     * @return
     */

    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordHasher.verifyPassword(hashedPassword, rawPassword);
    }

}
