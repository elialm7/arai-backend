package org.arai.Service;
import org.arai.Entities.Usuario;
import org.arai.Exceptions.IncorrectPasswordUsuarioException;
import org.arai.Exceptions.UsuarioNoEncontradoException;
import org.arai.Persistence.UsuarioRepository;
import org.arai.crypto.PasswordHasher;
import org.arai.jwt.JwtManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordHasher  passwordHasher;
    private final UsuarioRepository usuarioRepository;
    private final JwtManager jwtManager;

    public AuthService(UsuarioRepository usuarioRepository, PasswordHasher passwordHasher, JwtManager jwtManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordHasher = passwordHasher;
        this.jwtManager = jwtManager;
    }

    public String token_login(String cedula, String password) throws
            UsuarioNoEncontradoException,
            IncorrectPasswordUsuarioException {
        Usuario usuario =  usuarioRepository.findUsuarioByCedula(cedula);
        if(!usuario.getPassword().equalsIgnoreCase(password)){
            throw new IncorrectPasswordUsuarioException("Credenciales invalidas, password incorrecto");
        }
        return jwtManager.generateToken(usuario.getId_user().toString());
    }

    public String hashPassword(String rawPassword) {
        return passwordHasher.hashPassword(rawPassword);
    }

    public boolean  validarLogin(String user_password, String user_database_password) {
        return passwordHasher.matchesPassword(user_password, user_database_password);
    }


}
