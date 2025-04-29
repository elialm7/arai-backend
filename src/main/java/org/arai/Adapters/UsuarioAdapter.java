package org.arai.Adapters;

import org.arai.Entities.Usuario;
import org.arai.Model.usuarioDTO.CrearUsuarioDTO;


public class UsuarioAdapter {


    /**
     * transforma del dto a la entitdad, no agrega el password debido a que este se va a hashear antes de ser insertado a la base de datos
     * @param crearUsuarioDTO
     * @return
     */
    public static Usuario  deCrearUsuar(CrearUsuarioDTO crearUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setUsername(crearUsuarioDTO.username());
        usuario.setCorreo(crearUsuarioDTO.correo());
        usuario.setApellido(crearUsuarioDTO.apellido());
        usuario.setNombre(crearUsuarioDTO.nombre());
        return usuario;
    }
}
