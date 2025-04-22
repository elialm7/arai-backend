package org.arai.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDTO {

    @JsonProperty("token")
    private String token;
    @JsonProperty("tipo_token")
    private String tipoToken;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("apellido")
    private String apellido;
    @JsonProperty("correo")
    private String correo;
    @JsonProperty("role")
    private String rol;
    @JsonProperty("permisos")
    private List<String> permisos;

}
