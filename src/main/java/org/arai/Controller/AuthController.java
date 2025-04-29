package org.arai.Controller;

import java.util.Optional;
import java.util.stream.Collectors;

import org.arai.Entities.Permiso;
import org.arai.Entities.Usuario;
import org.arai.Exceptions.UsuarioNoEncontradoException;
import org.arai.Model.ErrorResponse.ErrorResponseDTO;
import org.arai.Model.login.LoginResponseDTO;
import org.arai.Model.login.LoginRequestDTO;
import org.arai.Service.AuthService;
import org.arai.Service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AuthService auth_service;
    @Autowired
    private UsuarioService user_service;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
       try {

           Optional<Usuario> search_user_opt = user_service.obtenerUsuarioPorUsername(loginRequestDTO.usename());


           if(search_user_opt.isEmpty()){
               return new ResponseEntity<>(
                       new ErrorResponseDTO("Usuario no encontrado", HttpStatus.NOT_FOUND.value()),
                       HttpStatus.NOT_FOUND
               );
           }
           Usuario found_user = search_user_opt.get();


           if(!auth_service.validarLogin(loginRequestDTO.password(), found_user.getPssword())){
               return new ResponseEntity<>(
                       new ErrorResponseDTO("Credenciales no validas", HttpStatus.UNAUTHORIZED.value()),
                       HttpStatus.UNAUTHORIZED
               );
           }

           LoginResponseDTO loginResponseDTO = new LoginResponseDTO(
                   found_user.getNombre(),
                   found_user.getApellido(),
                   found_user.getCorreo(),
                   found_user.getRol().getNombreRol(),
                   found_user.getRol().getPermisos().stream().map(Permiso::getNombrePermiso).collect(Collectors.toList())
           );

           return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);

       } catch (UsuarioNoEncontradoException e) {
           log.warn("Usuario no valido: {}", e.getMessage());
           return new ResponseEntity<>(
                   new ErrorResponseDTO("Usuario no Encontrado", HttpStatus.NOT_FOUND.value()),
                   HttpStatus.NOT_FOUND
           );
       }
    }

}
