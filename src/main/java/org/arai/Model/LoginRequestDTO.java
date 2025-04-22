package org.arai.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    @NotBlank(message = "El username no puede estar vacio")
    @NotNull(message = "El username no peude ser nulo")
    @JsonProperty("username")
    private String usename; 

    @NotBlank(message = "El password no puede estar vacio")
    @NotNull(message = "El password no puede ser nulo")
    @JsonProperty("password")
    private String password;
}
