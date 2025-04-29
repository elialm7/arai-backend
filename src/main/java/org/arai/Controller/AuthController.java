package org.arai.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.arai.Adapters.UsuarioAdapter;
import org.arai.Entities.Permiso;
import org.arai.Entities.Usuario;
import org.arai.Exceptions.UsuarioNoEncontradoException;
import org.arai.Model.LoginResponseDTO;
import org.arai.Model.LoginRequestDTO;
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


    //todo: manejar diferentas respuestas... LoginErrorResponseDTO
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
       try {
           /*boolean authorized = auth_service.validaCredenciales(loginRequestDTO);
           if(!authorized){
               log.warn("credenciales invalidas, Usuario no autorizado: {}", loginRequestDTO.usename());
               return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
           }*/

           Optional<Usuario> search_user_opt = user_service.obtenerUsuarioPorUsername(loginRequestDTO.usename());
           if(search_user_opt.isEmpty()){
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
           Usuario found_user = search_user_opt.get();

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
           return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
       }
    }

}
