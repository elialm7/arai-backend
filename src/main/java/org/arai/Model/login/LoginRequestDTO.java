package org.arai.Model.login;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(
        @NotBlank(message = "El username no puede estar vacio")
        @NotNull(message = "El username no peude ser nulo")
        @JsonProperty("cedula")
        String cedula,

        @NotBlank(message = "El password no puede estar vacio")
        @NotNull(message = "El password no puede ser nulo")
        @JsonProperty("password")
        String password
) {

}

