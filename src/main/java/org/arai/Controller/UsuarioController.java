package org.arai.Controller;

import jakarta.validation.Valid;
import org.arai.Model.usuarioDTO.CrearUsuarioDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {



    private Logger log = LoggerFactory.getLogger(UsuarioController.class);



    @PostMapping("/crearUsuario")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody CrearUsuarioDTO crearUsuarioDTO){



        return null;
    }


    @GetMapping
    public ResponseEntity<?> listarUsuario(){



        return null;

    }

}

