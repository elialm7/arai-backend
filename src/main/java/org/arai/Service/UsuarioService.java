package org.arai.Service;

import org.arai.Adapters.UsuarioAdapter;
import org.arai.Entities.Rol;
import org.arai.Entities.Usuario;
import org.arai.Exceptions.RoleNoEncontradoException;
import org.arai.Model.usuarioDTO.CrearUsuarioDTO;
import org.arai.Persistence.RolRepository;
import org.arai.Persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthService authService;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> obtenerUsuarioPorUsername(String username){
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        return repository.buscarPorUsername(usuario);
    }




    public  boolean CrearUsuario(CrearUsuarioDTO crearUsuarioDTO){
        if(Objects.isNull(crearUsuarioDTO)){
            throw new NullPointerException("No puede ser nulo");

        }
        Usuario newUser = UsuarioAdapter.deCrearUsuar(crearUsuarioDTO);
        newUser.setPssword(authService.hashPassword(crearUsuarioDTO.password()));

        Rol rol  =  rolRepository.encontrarPorId(crearUsuarioDTO.rol_id()).get();
        if(Objects.isNull(rol)) {
            throw new RoleNoEncontradoException("No se encontro el rol buscado");
        }
        newUser.setRol(rol);
        return usuarioRepository.agregarUsuario(newUser);

    }


}
