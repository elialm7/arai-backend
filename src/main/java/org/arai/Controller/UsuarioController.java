package org.arai.Controller;

import jakarta.validation.Valid;
import org.arai.Model.usuarioDTO.CrearUsuarioDTO;
import org.arai.Service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private Logger log = LoggerFactory.getLogger(UsuarioController.class);



    @PostMapping("/crearUsuario")
    public ResponseEntity<String> crearUsuario(@Valid @RequestBody CrearUsuarioDTO crearUsuarioDTO){

        log.debug("CrearUsuarioDTO {}", crearUsuarioDTO);
        boolean succes = usuarioService.CrearUsuario(crearUsuarioDTO);
        if(succes){
            return new ResponseEntity<>("Usuario creado exitosamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("Usuario no creado exitosamente", HttpStatus.BAD_REQUEST);

    }

}

