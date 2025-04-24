package org.arai.Controller;

import java.util.List;

import org.arai.Model.AuthResponseDTO;
import org.arai.Model.LoginRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private Logger log = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login( @Valid @RequestBody LoginRequestDTO loginRequestDTO){
        String token = "123456789";
        AuthResponseDTO responseDTO = new AuthResponseDTO();
        responseDTO.setNombre("Rodolfo Elias");
        responseDTO.setApellido("Ojeda Almada");
        responseDTO.setCorreo("metall65eli@gmail.com");
        responseDTO.setPermisos(List.of("VER_MI_PLANTEAMIENTO", "CREAR_PLANEAMIENTO"));
        responseDTO.setRol("DOCENTE");
        responseDTO.setTipoToken("JWT");
        responseDTO.setToken(token);
        log.warn("token ok");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
