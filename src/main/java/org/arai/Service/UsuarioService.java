package org.arai.Service;

import org.arai.Persistence.QueryResults.UsuarioPermisoResult;
import org.arai.Persistence.Repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }



    public UsuarioPermisoResult buscarPermisosPorUsuario(Integer usuario_id) {
        Optional<UsuarioPermisoResult> result = usuarioRepository.getUsuarioPermisoData(usuario_id);
        return result.get();
    }

}
