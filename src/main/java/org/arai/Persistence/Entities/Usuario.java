package org.arai.Persistence.Entities;

import lombok.Data;

@Data
public class Usuario {
    private Integer id_user;
    private Integer id_rol_fk;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private String username;
    private String cedula;
}
