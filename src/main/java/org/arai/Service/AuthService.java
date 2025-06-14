package org.arai.Service;
import org.arai.Persistence.Entities.Usuario;
import org.arai.Exceptions.IncorrectPasswordUsuarioException;
import org.arai.Exceptions.UsuarioNoEncontradoException;
import org.arai.Persistence.Repositories.UsuarioRepository;
import org.arai.Security.PasswordHasher;
import org.arai.Security.JwtManager;
import org.arai.Utilities.Pair;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Pair<String, Usuario> token_login_with_user(String cedula, String password) throws
            IncorrectPasswordUsuarioException {
        Optional<Usuario> usuario =  usuarioRepository.findUsuarioByCedula(cedula);
        if(usuario.isEmpty()){
            throw new UsuarioNoEncontradoException("Usuario no encontrado con la cedula: " + cedula);
        }
        if(!usuario.get().getPassword().equalsIgnoreCase(password)){
            throw new IncorrectPasswordUsuarioException("Credenciales invalidas, password incorrecto");
        }
        String token = jwtManager.generateToken(usuario.get().getId_user().toString());
        return new Pair<>(token, usuario.get());
    }

    @Deprecated
    public String hashPassword(String rawPassword) {
        return passwordHasher.hashPassword(rawPassword);
    }

    @Deprecated
    public boolean  validarLogin(String user_password, String user_database_password) {
        return passwordHasher.matchesPassword(user_password, user_database_password);
    }


}
