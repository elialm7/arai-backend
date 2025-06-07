package org.arai.Model.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record CrearUsuarioDTO(

        @NotBlank(message = "El username no puede ser nulo")
        @NotEmpty(message = "El username no puede estar vacio")
        @JsonProperty("username")
        String username,

        @NotEmpty(message = "El nombre no puede esta vacio")
        @NotBlank(message = "El usuario no puede tener nombre en blaco")
        @JsonProperty("nombre")
        String nombre,

        @NotBlank(message = "El apellido no puede estar en blanco")
        @NotEmpty(message = "El apellido no puede estar vacio")
        @JsonProperty("apellido")
        String apellido,

        @Email(message = "El email no cumple con el formato de correo")
        @NotEmpty(message = "El correo no puede estar vacio")
        @NotBlank(message = "El correo no puede estar en blanco")
        @JsonProperty("correo")
        String correo,

        @Size(min = 8, message = "El password debe ser por lo menos de 8 caracteres")
        @JsonProperty("password")
        String password,

        @NotNull(message = "El rol no puede ser nulo")
        @JsonProperty("rol_id")
        Integer rol_id
) {
}
