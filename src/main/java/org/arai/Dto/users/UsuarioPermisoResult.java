package org.arai.Dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPermisoResult {

    private Integer id_usuario;
    private String nombreRol;
    private List<String> permisos;

}
