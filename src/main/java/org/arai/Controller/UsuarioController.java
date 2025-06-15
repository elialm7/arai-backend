package org.arai.Controller;

import jakarta.validation.Valid;
import org.arai.Annotations.JwtClaim;
import org.arai.Exceptions.UsuarioYaExisteException;
import org.arai.Model.ErrorResponse.ErrorResponse;
import org.arai.Model.JwtClaim.JwtAudit;
import org.arai.Model.User.CrearUsuarioDTO;
import org.arai.Model.User.EditarUsuarioDTO;
import org.arai.Service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@JwtClaim JwtAudit claims, @Valid @RequestBody CrearUsuarioDTO crearUsuarioDTO){
        try {
            logger.info("Creando usuario: {}", crearUsuarioDTO.username());
            Integer usuarioId = usuarioService.crearUsuario(crearUsuarioDTO);
            logger.info("Usuario creado con ID: {}", usuarioId);
            return new ResponseEntity<>("Usuario creado con ID: " + usuarioId, HttpStatus.CREATED);
        }catch (UsuarioYaExisteException e){
            logger.error("Error al crear usuario: {}", e.getMessage());
            return new ResponseEntity<>(
                    new ErrorResponse("Usuario con cedula ya existe : "+ crearUsuarioDTO.cedula(), HttpStatus.CONFLICT.value()),
                    HttpStatus.CONFLICT
            );
        } catch (Exception e) {
            logger.error("Error al crear usuario: {}", e.getMessage());
            return new ResponseEntity<>(
                    new ErrorResponse("Error al crear usuario", HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarUsuario(@JwtClaim JwtAudit claims,
                                               @Valid @RequestBody EditarUsuarioDTO editarUsuarioDTO){
        usuarioService.actualizarUsuario(editarUsuarioDTO);

        return new ResponseEntity<>(
                "Usuario actualizado con ID: " + editarUsuarioDTO.id_usuario(),
                HttpStatus.OK
        );
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable("id") Integer idUsuario,
                                             @JwtClaim JwtAudit claims){
        usuarioService.eliminarUsuario(idUsuario);
        return new ResponseEntity<>("Usuario eliminado con ID: " + idUsuario, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarUsuario(@PathVariable("id") Integer idUsuario,
                                           @JwtClaim JwtAudit claims){
        return new ResponseEntity<>(
                usuarioService.buscarUsuarioPorId(idUsuario),
                HttpStatus.OK
        );
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarTodos(
            @RequestParam(name ="offset", defaultValue = "0") Integer offset,
            @RequestParam(name ="limit", defaultValue = "10") Integer limit,
            @JwtClaim JwtAudit claims
    ){
        //TODO: MANEJAR EXCEPCIONES
        return new ResponseEntity<>(
                usuarioService.buscarTodosUsuarioBatch(offset, limit),
                HttpStatus.OK
        );
    }

}

