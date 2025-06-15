package org.arai.Controller;

import org.arai.Exceptions.IncorrectPasswordUsuarioException;
import org.arai.Exceptions.UsuarioNoEncontradoException;
import org.arai.Exceptions.UsuarioPermitResultException;
import org.arai.Model.ErrorResponse.ErrorResponse;
import org.arai.Model.User.LoginRequest;
import org.arai.Model.User.LoginResponse;
import org.arai.Persistence.Entities.Usuario;
import org.arai.Persistence.QueryResults.UsuarioPermisoResult;
import org.arai.Service.AuthService;
import org.arai.Service.UsuarioService;
import org.arai.Utilities.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

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
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequestDTO){
       try {

              log.info("Intentando login para usuario: {}", loginRequestDTO.cedula());
           Pair<String, Usuario> tokenUser = auth_service.token_login_with_user(loginRequestDTO.cedula(), loginRequestDTO.password());
           UsuarioPermisoResult usuarioPermisos = usuario_service.buscarPermisosPorUsuario(tokenUser.second().getId_user());
           log.info("Usuario autenticado: {}", tokenUser.second().getId_user());
           return new ResponseEntity<>(
                   new LoginResponse(
                           tokenUser.second().getNombre(),
                           tokenUser.second().getApellido(),
                           tokenUser.second().getCorreo(),
                           usuarioPermisos.getNombreRol(),
                           usuarioPermisos.getPermisos(),
                           tokenUser.first()
                   ),
                   HttpStatus.OK
           );

       } catch (UsuarioNoEncontradoException e) {
           log.warn("Usuario no valido: {}", e.getMessage());
           return new ResponseEntity<>(
                   new ErrorResponse("Usuario no Encontrado", HttpStatus.NOT_FOUND.value()),
                   HttpStatus.NOT_FOUND
           );
       } catch (IncorrectPasswordUsuarioException e) {
           return  new ResponseEntity<>(
             new ErrorResponse("Password incorrecto", HttpStatus.FORBIDDEN.value()),
             HttpStatus.FORBIDDEN
           );
       }catch(UsuarioPermitResultException e){
              log.warn("Usuario no tiene permisos: {}", e.getMessage());
              return new ResponseEntity<>(
                     new ErrorResponse("Usuario no tiene permisos", HttpStatus.FORBIDDEN.value()),
                     HttpStatus.FORBIDDEN
              );
       } catch (Exception e) {
              log.error("Error en el login: {}", e.getMessage());
              return new ResponseEntity<>(
                     new ErrorResponse("Error de autenticacion", HttpStatus.INTERNAL_SERVER_ERROR.value()),
                     HttpStatus.INTERNAL_SERVER_ERROR
              );
       }
    }


}
