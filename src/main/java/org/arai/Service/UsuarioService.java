package org.arai.Service;

import org.arai.Entities.Usuario;
import org.arai.Exceptions.UsuarioNoEncontradoException;
import org.arai.Model.login.LoginRequestDTO;
import org.arai.Model.login.LoginResponseDTO;
import org.arai.Persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


}
