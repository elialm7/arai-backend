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

    public Usuario createAdminUser(){
        Usuario usuario = new Usuario();
        usuario.setCedula("-1");
        usuario.setNombre("ADMIN");
        usuario.setApellido("ADMIN");
        usuario.setUsername("ADMIN");
        usuario.setCorreo("Admin@hakesystems.com");
        usuario.setPassword("hakaADM");
        usuario.setId_user(1);
        usuario.setId_rol_fk(1);
        return usuario;
    }

    public Usuario searchUsuairo(LoginRequestDTO loginRequestDTO) throws UsuarioNoEncontradoException{

        Usuario userResult = usuarioRepository.findUsuarioByCedula(loginRequestDTO.cedula());

        if(Objects.isNull(userResult)){
            throw new UsuarioNoEncontradoException("Usuario no encontrado");
        }

        return userResult;
    }


}
