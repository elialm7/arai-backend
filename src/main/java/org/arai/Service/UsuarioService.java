package org.arai.Service;

import io.vavr.control.Try;
import org.arai.Exceptions.UsuarioPermitResultException;
import org.arai.Exceptions.UsuarioYaExisteException;
import org.arai.Model.User.CrearUsuarioDTO;
import org.arai.Persistence.Entities.Usuario;
import org.arai.Persistence.QueryResults.UsuarioPermisoResult;
import org.arai.Persistence.Repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AuditoriaService auditoriaService;
    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    public UsuarioService(UsuarioRepository usuarioRepository, AuditoriaService auditoriaService) {
        this.usuarioRepository = usuarioRepository;
        this.auditoriaService = auditoriaService;
    }



    public UsuarioPermisoResult buscarPermisosPorUsuario(Integer usuario_id) throws UsuarioPermitResultException {
        Optional<UsuarioPermisoResult> result = usuarioRepository.getUsuarioPermisoData(usuario_id);
        if (result.isEmpty()){
            logger.warn("Datos de permisos no encontrados para el usuario con ID: {}", usuario_id);
            throw new UsuarioPermitResultException("No se encontraron datos de permisos para el usuario con ID: " + usuario_id);
        }
        return result.get();
    }

    public Integer crearUsuario(CrearUsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.nombre());
        usuario.setApellido(usuarioDTO.apellido());
        usuario.setUsername(usuarioDTO.username());
        usuario.setPassword(usuarioDTO.password());
        usuario.setCorreo(usuarioDTO.correo());
        usuario.setCedula(usuarioDTO.cedula());
        usuario.setId_rol_fk(usuarioDTO.rol_id());

        logger.info("Creando usuario: {}", usuario.getUsername());
        Optional<Usuario> existingUser = usuarioRepository.findUsuarioByCedula(usuario.getCedula());
        if (existingUser.isPresent()) {
            logger.warn("El usuario con cedula {} ya existe.", usuario.getCedula());
            throw new UsuarioYaExisteException("El usuario con cedula " + usuario.getCedula() + " ya existe.");
        }
        Integer newId = usuarioRepository.insertUsuario(usuario);
        logger.info("Nuevo usuario creado con ID: {}", newId);
        return newId;
    }

    public List<Usuario> buscarTodosUsuarioBatch(Integer offset, Integer limit) {
        List<Usuario> usuarios = usuarioRepository.findAllUsuariosBatch(offset, limit);
        if (usuarios.isEmpty()) {
            logger.warn("No se encontraron usuarios en la base de datos.");
        } else {
            logger.info("Se encontraron {} usuarios en la base de datos.", usuarios.size());
        }
        return usuarios;
    }

}
