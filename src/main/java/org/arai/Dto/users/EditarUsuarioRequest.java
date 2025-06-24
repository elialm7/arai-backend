package org.arai.Dto.users;


import com.fasterxml.jackson.annotation.JsonProperty;

public record EditarUsuarioRequest(
        @JsonProperty("id_usuario")
        Integer id_usuario,
        @JsonProperty("username")
        String username,
        @JsonProperty("nombre")
        String nombre,
        @JsonProperty("apellido")
        String apellido,
        @JsonProperty("correo")
        String correo,
        @JsonProperty("password")
        String password,
        @JsonProperty("rol_id")
        Integer rol_id,
        @JsonProperty("cedula")
        String cedula
) {
}
