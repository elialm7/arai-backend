package org.arai.Controller;

import org.arai.Annotations.JwtClaim;
import org.arai.Entities.Usuario;
import org.arai.Exceptions.IncorrectPasswordUsuarioException;
import org.arai.Exceptions.UsuarioNoEncontradoException;
import org.arai.Model.ErrorResponse.ErrorResponseDTO;
import org.arai.Model.JwtClaims;
import org.arai.Model.login.LoginRequestDTO;
import org.arai.Model.login.LoginResponseDTO;
import org.arai.Service.AuthService;
import org.arai.Service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService auth_service;
    private final UsuarioService usuario_service;

    public AuthController(AuthService auth_service, UsuarioService usuario_service) {
        this.auth_service = auth_service;
        this.usuario_service = usuario_service;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
       try {
           String token = auth_service.token_login(loginRequestDTO.cedula(), loginRequestDTO.password());
           return new ResponseEntity<>(
                   new LoginResponseDTO(
                           "Director",
                           "Velazquez",
                           "DOCENTE@DOCENTE.com",
                           "DOCENTE",
                           List.of("VER_USUARIOS", "VER_PLANEAMIENTOS"),
                           token

                   )
                   ,
                   HttpStatus.OK
           );

       } catch (UsuarioNoEncontradoException e) {
           log.warn("Usuario no valido: {}", e.getMessage());
           return new ResponseEntity<>(
                   new ErrorResponseDTO("Usuario no Encontrado", HttpStatus.NOT_FOUND.value()),
                   HttpStatus.NOT_FOUND
           );
       } catch (IncorrectPasswordUsuarioException e) {
           return  new ResponseEntity<>(
             new ErrorResponseDTO("Password incorrecto", HttpStatus.FORBIDDEN.value()),
             HttpStatus.FORBIDDEN
           );
       }
    }

    @GetMapping("/pruebaToken")
    public ResponseEntity<?>  testToken(
            @JwtClaim JwtClaims claims
            ){


        log.info("Token: {}", claims);

        return new ResponseEntity<>("Token valido", HttpStatus.OK);



    }

}
