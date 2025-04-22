package org.arai.Service;

import org.arai.Entities.Usuario;
import org.arai.Persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository repository;

    public Optional<Usuario> obtenerUsuarioPorUsername(String username){
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        return repository.buscarPorUsername(usuario);
    }


    public List<Usuario> ObtenerTodosLosUsuarios(){
        return repository.obtenertodos();
    }


}
