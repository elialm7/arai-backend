package org.arai.Model.User;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(

        @JsonProperty("nombre")
        String nombre,
        @JsonProperty("apellido")
        String apellido,
        @JsonProperty("correo")
        String correo,
        @JsonProperty("role")
        String rol,
        @JsonProperty("permisos")
        List<String> permisos,
        @JsonProperty("token")
        String token
) {

}
