package org.arai.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public record LoginResponseDTO(

        @JsonProperty("nombre")
        String nombre,
        @JsonProperty("apellido")
        String apellido,
        @JsonProperty("correo")
        String correo,
        @JsonProperty("role")
        String rol,
        @JsonProperty("permisos")
        List<String> permisos
) {

}
