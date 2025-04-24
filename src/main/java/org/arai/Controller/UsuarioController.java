package org.arai.Controller;

import jakarta.validation.Valid;
import org.arai.Entities.Usuario;
import org.arai.Model.CrearUsuarioDTO;
import org.arai.Service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private Logger log = LoggerFactory.getLogger(UsuarioController.class);



    @PostMapping("/crearUsuario")
    public ResponseEntity<String> crearUsuario(@Valid @RequestBody CrearUsuarioDTO crearUsuarioDTO){

        log.warn("CrearUsuarioDTO {}", crearUsuarioDTO);
        return new ResponseEntity<>("Usuario creado :D", HttpStatus.CREATED);

    }

}

